package com.example.hellospringwebscopes;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SessionScopeTest {

    @LocalServerPort
    private int port;

    @Value("${test.base-url}")
    private String baseUrl;

    /**
     * "세션이 같은 경우"에 대한 테스트
     *
     * 배경 지식:
     * - 브라우저 세션: 웹 서버와 브라우저 간의 지속적인 연결 상태를 의미합니다.
     * - HTTP 쿠키: 웹 서버는 세션을 유지하기 위해 HTTP 쿠키를 사용합니다. 서버는 브라우저에 세션 ID를 담은 쿠키를 전송하고,
     * 브라우저는 이후 모든 요청에 해당 쿠키를 포함시켜 서버에게 자신이 누구인지 알려줍니다.
     * - RestTemplate: Spring에서 제공하는 HTTP 클라이언트로, 웹 애플리케이션에 HTTP 요청을 보내고 응답을 받을 수
     * 있습니다.
     * - TestRestTemplate은 Spring Boot에서 제공하는 테스트용 HTTP 클라이언트로, 웹 애플리케이션에 HTTP 요청을
     * 보내고 응답을 받을 수 있습니다.
     * TestRestTemplate은 간편한 설정을 제공하지만, 세션 관리를 위해서는 RestTemplate을 직접 사용하는 것이 더
     * 효과적입니다.
     * - CookieStore: HTTP 쿠키를 저장하고 관리하는 인터페이스입니다.
     *
     * 테스트 목표:
     * - 동일한 RestTemplate 객체를 사용하여 여러 번 요청을 보낼 때, 서버는 동일한 세션 ID를 유지해야 합니다.
     * - 세션 스코프 빈은 동일한 세션 내에서 항상 같은 인스턴스를 반환해야 합니다.
     */
    @Test
    public void testSameSessionScope() {
        // 쿠키 저장소 생성
        CookieStore cookieStore = new BasicCookieStore();

        // HttpClient 생성 및 쿠키 저장소 설정
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .build();

        // ClientHttpRequestFactory 생성 및 HttpClient 설정
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

        // RestTemplate 생성 및 ClientHttpRequestFactory 설정
        RestTemplate restTemplate = new RestTemplate(requestFactory);

        // URL 생성
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .port(port)
                .path("/session")
                .toUriString();

        // 첫 번째 요청으로 세션 생성 및 해시 코드 확인
        ResponseEntity<String> response1 = restTemplate.getForEntity(url, String.class);
        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);
        String message1 = response1.getBody();
        assertThat(message1).contains("Hello from Session Scope! (HashCode: ");

        // 두 번째 요청으로 동일한 세션에서 해시 코드 확인
        ResponseEntity<String> response2 = restTemplate.getForEntity(url, String.class);
        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.OK);
        String message2 = response2.getBody();
        assertThat(message2).contains("Hello from Session Scope! (HashCode: ");

        // 세션 스코프는 동일한 세션에서 항상 같은 인스턴스를 반환해야 함
        assertEquals(message1, message2);
    }

    /**
     * "세션이 다른 경우"에 대한 테스트
     *
     * 배경 지식:
     * - 각 RestTemplate 객체는 독립적인 세션을 가집니다.
     * - 서로 다른 RestTemplate 객체를 사용하여 요청을 보내면, 서버는 각각 다른 세션 ID를 할당합니다.
     *
     * 테스트 목표:
     * - 서로 다른 RestTemplate 객체를 사용하여 요청을 보낼 때, 서버는 서로 다른 세션 ID를 할당해야 합니다.
     * - 세션 스코프 빈은 서로 다른 세션에서 다른 인스턴스를 반환해야 합니다.
     */
    @Test
    public void testDifferentSessionScope() {
        // URL 생성
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .port(port)
                .path("/session")
                .toUriString();

        // 첫 번째 RestTemplate 생성 및 요청
        RestTemplate restTemplate1 = new RestTemplate();
        ResponseEntity<String> response1 = restTemplate1.getForEntity(url, String.class);
        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);
        String message1 = response1.getBody();
        assertThat(message1).contains("Hello from Session Scope! (HashCode: ");

        // 두 번째 RestTemplate 생성 및 요청
        RestTemplate restTemplate2 = new RestTemplate();
        ResponseEntity<String> response2 = restTemplate2.getForEntity(url, String.class);
        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.OK);
        String message2 = response2.getBody();
        assertThat(message2).contains("Hello from Session Scope! (HashCode: ");

        // 세션 스코프는 서로 다른 세션에서 다른 인스턴스를 반환해야 함
        assertNotEquals(message1, message2);
    }
}