package com.example.factorymethod;

public class HelloServiceImpl implements HelloService {
    private String message;

    public HelloServiceImpl(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "Hello, " + message + "!";
    }
}