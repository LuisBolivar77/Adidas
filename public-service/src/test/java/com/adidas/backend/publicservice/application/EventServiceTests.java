package com.adidas.backend.publicservice.application;

import com.adidas.backend.publicservice.infrastructure.driven_adapter.PublisherGateway;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EventServiceTests {

    @Test
    public void shouldCreateAnEventForKafka() {
        PublisherGateway publisherGatewaySpy = Mockito.spy(PublisherGateway.class);
        doNothing().when(publisherGatewaySpy).publish(Mockito.any());

        EventService event = new EventService(publisherGatewaySpy);
        event.eventCreator("test@testing.com");

        verify(publisherGatewaySpy).publish(Mockito.any());
    }
}
