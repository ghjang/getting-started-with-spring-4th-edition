package com.example;

import org.springframework.stereotype.Service;

@Service // 이 클래스를 서비스 빈으로 등록
public class HelloService {
    public String getMessage() {
        return "Hello, Spring!";
    }
}