package com.example.simpleparent;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleParentTest {

    @Test
    public void testBeanA() {
        try (var ctx = new ClassPathXmlApplicationContext(
                "META-INF/spring/applicationContext.xml")) {
            var beanA = ctx.getBean("beanA", BeanA.class);
            assertEquals("Base Value 1", beanA.getProperty1());
            assertEquals("Base Value 2", beanA.getProperty2());
            assertEquals("Value 3 (from BeanA)", beanA.getProperty3());
        }
    }

    @Test
    public void testBeanB() {
        try (var ctx = new ClassPathXmlApplicationContext(
                "META-INF/spring/applicationContext.xml")) {
            var beanB = ctx.getBean("beanB", BeanB.class);
            assertEquals("Base Value 1", beanB.getProperty1());
            assertEquals("Overridden Value 2", beanB.getProperty2());
            assertEquals("Value 3 (from BeanB)", beanB.getProperty3());
        }
    }

    @Test
    public void testBeanC() {
        try (var ctx = new ClassPathXmlApplicationContext(
                "META-INF/spring/applicationContext.xml")) {
            var beanC = ctx.getBean("beanC", BeanC.class);
            assertEquals("Base Value 1", beanC.getProperty1());
            assertEquals("Overridden Value 2", beanC.getProperty2());
            assertEquals("New Value 3", beanC.getProperty3());
            assertEquals("Value 4", beanC.getProperty4());
        }
    }
}