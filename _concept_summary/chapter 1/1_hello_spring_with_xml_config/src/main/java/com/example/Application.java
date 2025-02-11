package com.example;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                "META-INF/spring/applicationContext.xml")) {

            HelloBean helloBean = (HelloBean) ctx.getBean("helloBean");
            helloBean.printMessage();
        }
    }
}