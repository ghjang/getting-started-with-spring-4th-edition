package com.example.factorymethod;

public interface HelloService {
    String getMessage();

    // 정적 팩토리 메서드 방식
    static HelloService createInstance(String message) {
        return new HelloServiceImpl(message);
    }
}