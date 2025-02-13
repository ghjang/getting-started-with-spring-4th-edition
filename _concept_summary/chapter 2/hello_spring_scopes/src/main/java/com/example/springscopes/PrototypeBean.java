package com.example.springscopes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Scope("prototype")
public class PrototypeBean {
    private String message;

    @Autowired
    public PrototypeBean() {
        System.out.println("PrototypeBean 생성됨!");
    }

    @PostConstruct
    public void initialize() {
        System.out.println("PrototypeBean 초기화됨!");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("PrototypeBean 소멸됨!");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}