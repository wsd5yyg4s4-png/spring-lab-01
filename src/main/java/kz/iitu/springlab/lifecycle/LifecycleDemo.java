package kz.iitu.springlab.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class LifecycleDemo {

    private final DateTimeFormatter formatter;
    private final List<String> events = new ArrayList<>();

    public LifecycleDemo(DateTimeFormatter formatter) {
        this.formatter = formatter;
        record("1. Constructor called");
    }

    @PostConstruct
    void init() {
        record("2. @PostConstruct executed");
    }

    @PreDestroy
    void destroy() {
        record("3. @PreDestroy executed");
    }

    public List<String> events() {
        return List.copyOf(events);
    }

    private void record(String stage) {
        events.add(LocalDateTime.now().format(formatter) + " " + stage);
    }
}