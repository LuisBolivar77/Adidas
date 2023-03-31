package com.adidas.backend.publicservice.infrastructure;

import com.adidas.backend.publicservice.application.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest
public class SubscriptionRestControllerTests {

    @MockBean
    private EventService eventService;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private ApplicationContext applicationContext;


    @BeforeEach
    void init() {
        webTestClient = WebTestClient.bindToApplicationContext(applicationContext).build();
    }

    @Test
    void shouldSubscribeWhenSuccessful() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/saleSubscribe")
                        .queryParam("email", "armando@thejavamaster.com")
                        .build())
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    @Test
    void shouldRetrieveExceptionWhenMissingURIParam() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/v1/saleSubscribe")
                        .build())
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }
}
