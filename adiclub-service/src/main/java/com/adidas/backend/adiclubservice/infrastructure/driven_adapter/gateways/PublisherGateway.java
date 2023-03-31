package com.adidas.backend.adiclubservice.infrastructure.driven_adapter.gateways;

import com.adidas.backend.adiclubservice.domain.Event;

public interface PublisherGateway {

    void publishMemberPriority(Event event);

    void publishException(Event event);
}
