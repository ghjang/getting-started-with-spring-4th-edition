package com.example.daofactory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                "applicationContext.xml")) {
            ArticleDao articleDao = ctx.getBean("articleDao", ArticleDao.class);
            Article article = articleDao.getArticle(1);
            System.out.println(article);
        }
    }
}
