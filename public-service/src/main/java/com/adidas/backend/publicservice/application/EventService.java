package com.adidas.backend.publicservice.application;

import com.adidas.backend.publicservice.domain.Event;
import com.adidas.backend.publicservice.domain.EventType;
import com.adidas.backend.publicservice.infrastructure.driven_adapter.PublisherGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {
    private final PublisherGateway publisherGateway;

    public void eventCreator(String email) {
        var subscriptionEvent = Event.builder()
                .id(UUID.randomUUID().toString())
                .type(EventType.CREATED)
                .date(new Date())
                .data(email)
                .build();

        publisherGateway.publish(subscriptionEvent);
    }
}
