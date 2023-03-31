package com.adidas.backend.publicservice.infrastructure.driven_adapter;

import com.adidas.backend.publicservice.domain.Event;
import org.apache.kafka.common.config.ConfigException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
public class PublisherAdapter implements PublisherGateway {

    @Autowired
    private KafkaTemplate<String, Object> producer;

    @Value("${topic.adiClub.name}")
    private String adiClubTopic;

    @Retryable(value = { ConfigException.class }, maxAttempts = 3, backoff = @Backoff(delay = 60000))
    public void publish(Event event) {
        this.producer.send(adiClubTopic, event);
    }
}
