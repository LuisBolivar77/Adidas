package com.adidas.backend.prioritysaleservice.infrastructure.driven_adapter.gateways;

import com.adidas.backend.prioritysaleservice.domain.Event;

public interface PublisherGateway {

    void publishException(Event event);
}
