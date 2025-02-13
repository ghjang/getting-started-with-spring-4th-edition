package com.example.springscopes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertSame;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class SingletonTest {
    @Autowired
    private ApplicationContext context;

    @Test
    public void testSingletonScope() {
        SingletonBean bean1 = context.getBean(SingletonBean.class);
        SingletonBean bean2 = context.getBean(SingletonBean.class);

        assertSame("싱글톤 스코프는 항상 같은 인스턴스를 반환해야 합니다.", bean1, bean2);
    }
}
