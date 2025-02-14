package com.example.daofactory;

public class ArticleJdbcDao implements ArticleDao {
    @Override
    public Article getArticle(int id) {
        // JDBC를 사용하여 데이터베이스에서 Article 정보를 가져오는 코드
        System.out.println("Using JDBC to get Article");
        return new Article(id, "JDBC Article", "JDBC Content");
    }
}