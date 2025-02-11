package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
                AppConfig.class)) {
            HelloBean helloBean = (HelloBean) ctx.getBean("helloBean");
            helloBean.printMessage();
        }
    }
}