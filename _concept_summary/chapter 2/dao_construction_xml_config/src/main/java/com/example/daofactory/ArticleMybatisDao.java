package com.example.daofactory;

public class ArticleMybatisDao implements ArticleDao {
    @Override
    public Article getArticle(int id) {
        // MyBatis를 사용하여 데이터베이스에서 Article 정보를 가져오는 코드
        System.out.println("Using MyBatis to get Article");
        return new Article(id, "MyBatis Article", "MyBatis Content");
    }
}