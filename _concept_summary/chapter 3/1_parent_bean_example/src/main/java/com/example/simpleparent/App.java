package com.example.simpleparent;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        try (var ctx = new ClassPathXmlApplicationContext(
                "META-INF/spring/applicationContext.xml")) {
            var beanA = ctx.getBean("beanA", BeanA.class);
            var beanB = ctx.getBean("beanB", BeanB.class);
            var beanC = ctx.getBean("beanC", BeanC.class);

            System.out.println("beanA = " + beanA);
            System.out.println("beanB = " + beanB);
            System.out.println("beanC = " + beanC);
        }
    }
}