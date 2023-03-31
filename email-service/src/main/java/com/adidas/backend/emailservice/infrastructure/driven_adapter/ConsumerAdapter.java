package com.adidas.backend.emailservice.infrastructure.driven_adapter;

import com.adidas.backend.emailservice.application.SubscriptionService;
import com.adidas.backend.emailservice.domain.Event;
import lombok.RequiredArgsConstructor;
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
    @KafkaListener(
            topics = "exception",
            groupId = "groupId",
            containerFactory = "factory"
    )
    public void consumerException(Event event) {
        subscriptionService.notifyUser(event.getData());
    }

}
