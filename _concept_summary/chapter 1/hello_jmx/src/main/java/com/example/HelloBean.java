// HelloBean.java
package com.example;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(objectName = "com.example:type=HelloBean", description = "Hello Bean")
public class HelloBean {
    private String message = "Hello, JMX!";

    @ManagedAttribute(description = "Message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}