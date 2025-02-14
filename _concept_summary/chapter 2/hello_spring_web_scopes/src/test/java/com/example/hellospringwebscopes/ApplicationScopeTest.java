package com.example.hellospringwebscopes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationScopeTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testApplicationScope() {
        ResponseEntity<String> response1 = restTemplate.getForEntity("/application", String.class);
        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);
        String message1 = response1.getBody();
        assertThat(message1).contains("Hello from Application Scope! (HashCode: ");

        ResponseEntity<String> response2 = restTemplate.getForEntity("/application", String.class);
        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.OK);
        String message2 = response2.getBody();
        assertThat(message2).contains("Hello from Application Scope! (HashCode: ");

        assertEquals(message1, message2); // 이전 요청과 해시 코드가 같은지 확인
    }
}