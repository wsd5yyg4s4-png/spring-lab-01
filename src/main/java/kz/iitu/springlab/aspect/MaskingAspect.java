package kz.iitu.springlab.aspect;

import kz.iitu.springlab.audit.Sensitive;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class MaskingAspect {

    @Before("execution(public * kz.iitu.springlab.service..*(..))")
    public void maskArguments(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();
        Method method = ((org.aspectj.lang.reflect.MethodSignature)
                joinPoint.getSignature()).getMethod();

        java.lang.annotation.Annotation[][] annotations =
                method.getParameterAnnotations();

        String[] maskedArgs = new String[args.length];

        for (int i = 0; i < args.length; i++) {

            boolean sensitive = Arrays.stream(annotations[i])
                    .anyMatch(a -> a.annotationType()
                            .equals(Sensitive.class));

            maskedArgs[i] = sensitive
                    ? "***"
                    : String.valueOf(args[i]);
        }

        System.out.println(
                "[MASK] " +
                        method.getName() +
                        " args=" +
                        Arrays.toString(maskedArgs)
        );
    }
}