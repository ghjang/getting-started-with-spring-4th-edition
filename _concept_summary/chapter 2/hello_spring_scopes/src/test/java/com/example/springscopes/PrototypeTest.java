package com.example.springscopes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotSame;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class PrototypeTest {
    @Autowired
    private ApplicationContext context;

    @Test
    public void testPrototypeScope() {
        PrototypeBean bean1 = context.getBean(PrototypeBean.class);
        PrototypeBean bean2 = context.getBean(PrototypeBean.class);

        assertNotSame("프로토타입 스코프는 항상 다른 인스턴스를 반환해야 합니다.", bean1, bean2);
    }
}