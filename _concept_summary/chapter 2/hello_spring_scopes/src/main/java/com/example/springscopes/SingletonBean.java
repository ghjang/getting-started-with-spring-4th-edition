package com.example.springscopes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Scope("singleton")
public class SingletonBean {
    private String message;

    @Autowired
    public SingletonBean() {
        System.out.println("SingletonBean 생성됨!");
    }

    @PostConstruct
    public void initialize() {
        System.out.println("SingletonBean 초기화됨!");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("SingletonBean 소멸됨!");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}