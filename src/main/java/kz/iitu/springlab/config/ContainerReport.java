package kz.iitu.springlab.config;

import kz.iitu.springlab.notify.Notifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ContainerReport implements CommandLineRunner {

    private final ApplicationContext context;

    public ContainerReport(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void run(String... args) {

        System.out.println("===== CONTAINER REPORT =====");
        System.out.println("Bean count: " + context.getBeanDefinitionCount());

        System.out.println("Notifier beans: " +
                Arrays.toString(context.getBeanNamesForType(Notifier.class)));

        System.out.println("============================");
    }
}