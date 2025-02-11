package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // 이 클래스를 스프링 빈으로 등록
public class HelloBean {

    @Autowired // HelloService 빈을 자동으로 주입
    private HelloService helloService;

    public void printMessage() {
        String message = helloService.getMessage();
        System.out.println(message);
    }
}