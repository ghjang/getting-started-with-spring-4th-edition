package com.example.factorymethod;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext.xml")) {

            // 생성자 주입 방식
            HelloService helloServiceConstructor = ctx.getBean("helloServiceConstructor", HelloService.class);
            String msgConstructor = helloServiceConstructor.getMessage();
            System.out.println(msgConstructor);

            // 정적 팩토리 메서드 방식
            HelloService helloServiceFactoryMethod = ctx.getBean("helloServiceFactoryMethod", HelloService.class);
            String msgFactoryMethod = helloServiceFactoryMethod.getMessage();
            System.out.println(msgFactoryMethod);

            // 인스턴스 팩토리 메서드 방식
            HelloService helloServiceInstanceFactory = ctx.getBean("helloServiceInstanceFactory", HelloService.class);
            String msgInstanceFactory = helloServiceInstanceFactory.getMessage();
            System.out.println(msgInstanceFactory);
        }
    }
}