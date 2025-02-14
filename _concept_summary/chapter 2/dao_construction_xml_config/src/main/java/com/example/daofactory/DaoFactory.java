package com.example.daofactory;

public class DaoFactory {
    private String daoType;

    public DaoFactory(String daoType) {
        this.daoType = daoType;
    }

    public ArticleDao getArticleDao() {
        if ("jdbc".equals(daoType)) {
            return new ArticleJdbcDao();
        } else if ("mybatis".equals(daoType)) {
            return new ArticleMybatisDao();
        } else {
            throw new IllegalArgumentException("Invalid DAO type: " + daoType);
        }
    }
}