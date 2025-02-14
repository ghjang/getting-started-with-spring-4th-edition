package com.example.hellospringwebscopes;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Scope("application")
public class ApplicationBean {
    private final int hashCode = System.identityHashCode(this);
    private String message = "Hello from Application Scope!";

    @PostConstruct
    public void initialize() {
        System.out.println("ApplicationBean 생성됨! HashCode: " + hashCode);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("ApplicationBean 소멸됨! HashCode: " + hashCode);
    }

    public String getMessage() {
        return message + " (HashCode: " + hashCode + ")";
    }

    public void setMessage(String message) {
        this.message = message;
    }
}