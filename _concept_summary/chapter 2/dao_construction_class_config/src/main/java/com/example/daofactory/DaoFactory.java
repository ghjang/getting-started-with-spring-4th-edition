package com.example.daofactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * DaoFactory 클래스는 ArticleDao 빈을 생성하는 팩토리 역할을 합니다.
 *
 * @Configuration 어노테이션은 이 클래스가 스프링 설정 클래스임을 나타냅니다.
 *                스프링은 이 클래스를 스캔하여 빈 정의를 찾고, 애플리케이션 컨텍스트를 구성합니다.
 *
 *                스프링 설정 파일은 XML 파일로 작성할 수도 있지만, 자바 클래스로 작성할 수도 있습니다.
 *                이러한 Configuration 어노테이션을 갖는 설정 클래스들은 1개 이상 존재할 수 있습니다.
 *
 *                여러 개의 설정 클래스를 사용하는 경우, 다음과 같은 방법으로 설정 클래스들을 등록할 수 있습니다.
 *                1. AnnotationConfigApplicationContext 생성자에 설정 클래스들을 인수로 전달
 *                예: AnnotationConfigApplicationContext ctx = new
 *                AnnotationConfigApplicationContext(DaoFactory.class,
 *                AnotherConfig.class);
 *                2. register() 메서드를 사용하여 설정 클래스들을 등록
 *                예: AnnotationConfigApplicationContext ctx = new
 *                AnnotationConfigApplicationContext();
 *                ctx.register(DaoFactory.class);
 *                ctx.register(AnotherConfig.class);
 *                ctx.refresh();
 *                3. @Import 어노테이션을 사용하여 다른 설정 클래스를 가져옴 (이 클래스에서 다른 설정 클래스를 가져오는
 *                경우)
 *                예: @Import({AnotherConfig.class, YetAnotherConfig.class})
 *
 * @PropertySource 어노테이션은 프로퍼티 파일을 로드하는 데 사용됩니다.
 *                 여기서는 classpath:application.properties 파일을 로드하여 설정 값을 사용합니다.
 *
 * @ComponentScan 어노테이션은 스프링이 컴포넌트를 스캔할 패키지를 지정합니다.
 *                스프링은 지정된 패키지에서 @Component, @Service, @Repository, @Controller
 *                등의 어노테이션이 붙은 클래스를 찾아 자동으로 빈으로 등록합니다.
 *                만약 여러 개의 설정 클래스에서 동일한 패키지를 스캔하도록 설정한 경우, 스프링은 해당 패키지를 한 번만
 *                스캔합니다.
 *
 * @Value 어노테이션은 프로퍼티 파일에서 값을 읽어와 필드에 주입하는 데 사용됩니다.
 *        여기서는 dao.type 프로퍼티 값을 읽어와 daoType 필드에 주입합니다.
 *
 * @Bean 어노테이션은 메서드가 빈을 생성하고 반환하는 역할을 한다는 것을 스프링에게 알려줍니다.
 *       스프링은 이 메서드를 호출하여 ArticleDao 빈을 생성하고, 애플리케이션 컨텍스트에 등록합니다.
 */
@Configuration
@ComponentScan("com.example.daofactory")
@PropertySource("classpath:application.properties")
@Import({})
public class DaoFactory {

    @Value("${dao.type}")
    private String daoType;

    @Bean
    public ArticleDao articleDao() {
        if ("jdbc".equals(daoType)) {
            return new ArticleJdbcDao();
        } else if ("mybatis".equals(daoType)) {
            return new ArticleMybatisDao();
        } else {
            throw new IllegalArgumentException("Invalid DAO type: " + daoType);
        }
    }
}