package com.adidas.backend.prioritysaleservice.application;

import com.adidas.backend.prioritysaleservice.domain.AdiClubMember;
import com.adidas.backend.prioritysaleservice.domain.Event;
import com.adidas.backend.prioritysaleservice.domain.EventType;
import com.adidas.backend.prioritysaleservice.domain.ThreadLocalData;
import com.adidas.backend.prioritysaleservice.infrastructure.driven_adapter.gateways.PublisherGateway;
import com.adidas.backend.prioritysaleservice.infrastructure.driven_adapter.gateways.SubscriptionGateway;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionGateway subscriptionGateway;
    private final PublisherGateway publisherGateway;

    public void saveSubscriptionMember(Event event) throws JsonProcessingException {
        var adiClubMember = eventDataConverter(event.getData());
        subscriptionGateway.saveSubscription(adiClubMember);
    }

    public AdiClubMember eventDataConverter(String data) throws JsonProcessingException {
        var objectMapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        return objectMapper.readValue(data, AdiClubMember.class);
    }

    public void publishException(String email) {
        publisherGateway.publishException(
                Event.builder()
                        .id(UUID.randomUUID().toString())
                        .type(EventType.UPDATED)
                        .date(new Date())
                        .data(email)
                        .build()
        );
    }
}
