package com.adidas.backend.emailservice;

import com.adidas.backend.emailservice.application.SubscriptionService;
import com.adidas.backend.emailservice.domain.AdiClubMember;
import com.adidas.backend.emailservice.domain.Event;
import com.adidas.backend.emailservice.domain.EventType;
import com.adidas.backend.emailservice.infrastructure.driven_adapter.gateway.EmailGateway;
import com.adidas.backend.emailservice.infrastructure.driven_adapter.gateway.SubscriptionGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SubscriptionServiceTests {

    @Mock
    private EmailGateway emailGateway;

    @Test
    public void shouldCreateAnEventForKafka() {
        var adiClubMember = AdiClubMember
                .builder()
                .email("test@testing.com")
                .build();

        var list = new ArrayList<AdiClubMember>();
        list.add(adiClubMember);


        SubscriptionGateway subscriptionGateway = Mockito.spy(SubscriptionGateway.class);
        doNothing().when(subscriptionGateway).deleteSubscription(Mockito.any());
        when(subscriptionGateway.getSubscriptionOrderedList()).thenReturn(list);

        SubscriptionService subscriptionService = new SubscriptionService(subscriptionGateway, emailGateway);
        subscriptionService.selectWinner();

        verify(subscriptionGateway).deleteSubscription(Mockito.any());
    }
}
