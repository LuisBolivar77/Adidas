package com.adidas.backend.adiclubservice;

import com.adidas.backend.adiclubservice.application.MemberService;
import com.adidas.backend.adiclubservice.domain.Event;
import com.adidas.backend.adiclubservice.domain.EventType;
import com.adidas.backend.adiclubservice.infrastructure.driven_adapter.gateways.PublisherGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.UUID;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EventServiceTests {

    @Test
    public void shouldCreateAnEventForKafka() {
        var eventKafka = Event
                .builder()
                .id(UUID.randomUUID().toString())
                .type(EventType.CREATED)
                .date(new Date())
                .data("test@testing.com")
                .build();


        PublisherGateway publisherGatewaySpy = Mockito.spy(PublisherGateway.class);
        doNothing().when(publisherGatewaySpy).publishMemberPriority(Mockito.any());

        MemberService event = new MemberService(publisherGatewaySpy);
        event.getAdiClubMemberInfo(eventKafka);

        verify(publisherGatewaySpy).publishMemberPriority(Mockito.any());
    }
}
