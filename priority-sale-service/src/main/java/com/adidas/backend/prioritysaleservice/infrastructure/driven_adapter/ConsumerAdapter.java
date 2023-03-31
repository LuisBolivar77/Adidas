package com.adidas.backend.prioritysaleservice.infrastructure.driven_adapter;

import com.adidas.backend.prioritysaleservice.application.SubscriptionService;
import com.adidas.backend.prioritysaleservice.domain.Event;
import com.adidas.backend.prioritysaleservice.domain.ThreadLocalData;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.kafka.common.config.ConfigException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsumerAdapter {

    private final SubscriptionService subscriptionService;

    @Retryable(value = { ConfigException.class }, maxAttempts = 3, backoff = @Backoff(delay = 60000))
    @SneakyThrows
    @KafkaListener(
            topics = "priority",
            groupId = "groupId",
            containerFactory = "factory"
    )
    public void consumerAdiClub(Event event) {
        var adiClubMember = subscriptionService.eventDataConverter(event.getData());
        ThreadLocalData.setValue(adiClubMember.getEmail());
        subscriptionService.saveSubscriptionMember(event);
    }

}
