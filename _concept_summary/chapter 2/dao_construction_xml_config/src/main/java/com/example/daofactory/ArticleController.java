package com.example.daofactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ArticleController {
    private ArticleService articleService;

    /*
     * NOTE:
     * 'applicationContext.xml'에서 'PropertyPlaceholderConfigurer'를
     * 사용하여 '스프링 컨테이너' 컨텍스트에 로딩한 'application.properties' 파일의
     * 'dao.type' 프로퍼티 값을 'daoType' 멤버 변수에 주입한다.
     *
     * 'AutowiredAnnotationBeanPostProcessor' 빈을 컨텍스트에 등록했기 때문에
     * 이렇게 '임의 .java' 파일에서 '@Value' 어노테이션을 사용하여
     * 'application.properties' 파일의 프로퍼티 값을 주입받을 수 있다.
     */
    @Value("${dao.type}")
    private String daoType;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    public Article getArticle(int id) {
        System.out.println("daoType from Controller: " + daoType);

        return articleService.getArticle(id);
    }
}