package com.example.daofactory;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
                DaoFactory.class)) { // (1) 설정 클래스를 지정하여 스프링 컨텍스트를 초기화

            ArticleController articleController = ctx.getBean(ArticleController.class);
            Article article = articleController.getArticle(1);
            System.out.println(article);
        }

        /*
         * (1) AnnotationConfigApplicationContext는 스프링 컨텍스트를 초기화하는 클래스입니다.
         *
         * - AnnotationConfigApplicationContext() : 기본 생성자를 사용하여 빈 컨텍스트를 생성합니다.
         * 이 경우, 컨텍스트를 사용하기 전에 반드시 register() 메서드와 refresh() 메서드를 호출해야 합니다.
         * 예시:
         * AnnotationConfigApplicationContext ctx = new
         * AnnotationConfigApplicationContext();
         * ctx.register(DaoFactory.class); // 설정 클래스 등록
         * ctx.scan("com.example.daofactory"); // 컴포넌트 스캔 (선택 사항)
         * ctx.refresh(); // 컨텍스트 초기화
         *
         * - AnnotationConfigApplicationContext(Class<?>... componentClasses) :
         * 설정 클래스를 직접 지정하여 스프링 컨텍스트를 초기화합니다.
         * 이 생성자를 사용하면 register() 메서드와 refresh() 메서드를 호출할 필요가 없습니다.
         * 예시:
         * AnnotationConfigApplicationContext ctx = new
         * AnnotationConfigApplicationContext(DaoFactory.class);
         *
         * 여러 개의 설정 클래스를 지정할 수도 있습니다.
         * 예시:
         * AnnotationConfigApplicationContext ctx = new
         * AnnotationConfigApplicationContext(DaoFactory.class, AnotherConfig.class,
         * YetAnotherConfig.class);
         *
         * 이 생성자를 사용하는 경우, 설정 클래스에서 @ComponentScan 어노테이션을 사용하여 컴포넌트를 스캔해야 합니다.
         *
         *
         * (2) 스프링 컨텍스트 초기화 방법
         *
         * - XML 설정 파일 사용:
         * ClassPathXmlApplicationContext 또는 FileSystemXmlApplicationContext 클래스를 사용하여
         * XML 설정 파일을 로드합니다.
         * 예시:
         * ApplicationContext ctx = new
         * ClassPathXmlApplicationContext("applicationContext.xml");
         *
         * - 애노테이션 기반 설정 사용:
         * AnnotationConfigApplicationContext 클래스를 사용하여 애노테이션 기반 설정을 활성화합니다.
         * 예시:
         * AnnotationConfigApplicationContext ctx = new
         * AnnotationConfigApplicationContext(AppConfig.class);
         *
         * (3) 컴포넌트 스캔
         *
         * - @ComponentScan 어노테이션:
         * 설정 클래스에서 @ComponentScan 어노테이션을 사용하여 스프링이 컴포넌트를 스캔할 패키지를 지정합니다.
         * 예시:
         * 
         * @Configuration
         * 
         * @ComponentScan("com.example.daofactory")
         * public class AppConfig { ... }
         *
         * - context.scan() 메서드:
         * 스프링 컨텍스트 객체의 scan() 메서드를 사용하여 컴포넌트를 스캔할 패키지를 지정합니다.
         * 이 방법은 @ComponentScan 어노테이션을 사용하는 대신, 코드를 통해 컴포넌트 스캔을 제어할 수 있습니다.
         * 예시:
         * AnnotationConfigApplicationContext ctx = new
         * AnnotationConfigApplicationContext();
         * ctx.scan("com.example.daofactory");
         * ctx.refresh();
         */
    }
}