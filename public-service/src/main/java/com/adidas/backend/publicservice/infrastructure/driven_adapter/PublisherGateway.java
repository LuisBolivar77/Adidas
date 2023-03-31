package com.adidas.backend.publicservice.infrastructure.driven_adapter;

import com.adidas.backend.publicservice.domain.Event;

public interface PublisherGateway {

    void publish(Event event);
}
