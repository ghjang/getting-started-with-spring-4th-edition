package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 이 클래스가 스프링 설정 클래스임을 나타냄
public class AppConfig {

    @Bean // 이 메서드가 빈을 생성하는 메서드임을 나타냄
    public HelloBean helloBean() {
        HelloBean helloBean = new HelloBean();
        helloBean.setMessage("Hello, Spring!");
        return helloBean;
    }
}