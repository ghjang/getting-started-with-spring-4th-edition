package com.example.springscopes;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
                AppConfig.class)) {
            SingletonBean bean1 = ctx.getBean(SingletonBean.class);
            bean1.setMessage("첫 번째 메시지");
            System.out.println("Bean 1: " + bean1.getMessage());

            SingletonBean bean2 = ctx.getBean(SingletonBean.class);
            bean2.setMessage("두 번째 메시지");
            System.out.println("Bean 2: " + bean2.getMessage());
        }
    }
}