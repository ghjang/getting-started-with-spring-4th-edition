package com.example.factorymethod;

import org.springframework.stereotype.Component;

@Component
public class HelloServiceFactory {
    public HelloService createInstance(String message) {
        return new HelloServiceImpl(message);
    }
}