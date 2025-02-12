// Application.java
package com.example;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        try (ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                "META-INF/spring/applicationContext.xml")) {
            HelloBean helloBean = (HelloBean) ctx.getBean("helloBean");
            System.out.println(helloBean.getMessage());

            // JMX 콘솔에서 MBean을 확인할 수 있도록 잠시 대기
            long threeMinutes = 3 * 60 * 1000;
            Thread.sleep(threeMinutes);
        }
    }
}