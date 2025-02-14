package com.example.daofactory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext.xml")) {
            ArticleController articleController = ctx.getBean("articleController", ArticleController.class);
            Article article = articleController.getArticle(1);
            System.out.println(article);
        }
    }
}