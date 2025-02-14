package com.example.hellospringwebscopes;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestBean {
    private final int hashCode = System.identityHashCode(this);
    private String message = "Hello from Request Scope!";

    @PostConstruct
    public void initialize() {
        System.out.println("RequestBean 생성됨! HashCode: " + hashCode);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("RequestBean 소멸됨! HashCode: " + hashCode);
    }

    public String getMessage() {
        return message + " (HashCode: " + hashCode + ")";
    }

    public void setMessage(String message) {
        this.message = message;
    }
}