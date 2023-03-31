package com.adidas.backend.prioritysaleservice;

import com.adidas.backend.prioritysaleservice.application.SubscriptionService;
import com.adidas.backend.prioritysaleservice.domain.AdiClubMember;
import com.adidas.backend.prioritysaleservice.domain.Event;
import com.adidas.backend.prioritysaleservice.domain.EventType;
import com.adidas.backend.prioritysaleservice.infrastructure.driven_adapter.gateways.PublisherGateway;
import com.adidas.backend.prioritysaleservice.infrastructure.driven_adapter.gateways.SubscriptionGateway;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

import static org.apache.kafka.test.TestUtils.RANDOM;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SubscriptionServiceTests {

    @Mock
    private SubscriptionGateway subscriptionGateway;

    @Mock
    private PublisherGateway publisherGateway;

    @InjectMocks
    private SubscriptionService subscriptionService;

    @Test
    public void shouldSaveASubscription() throws JsonProcessingException {
        var event = eventCreator();

        doNothing().when(subscriptionGateway).saveSubscription(Mockito.any());
        subscriptionService.saveSubscriptionMember(event);

        verify(subscriptionGateway).saveSubscription(Mockito.any());

    }

    public Event eventCreator() throws JsonProcessingException {
        var adiClubMember = AdiClubMember
                .builder()
                .uuid(UUID.randomUUID().toString())
                .points(213123)
                .email("test@test.com")
                .registrationDate(Instant.now().minus(RANDOM.nextInt(365), ChronoUnit.DAYS))
                .build();

        var objectMapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        var json = objectMapper.writeValueAsString(adiClubMember);

        return Event
                .builder()
                .id(UUID.randomUUID().toString())
                .type(EventType.CREATED)
                .date(new Date())
                .data(json)
                .build();


    }
}
