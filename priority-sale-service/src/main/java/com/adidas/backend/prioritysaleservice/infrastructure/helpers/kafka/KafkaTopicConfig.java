package com.adidas.backend.prioritysaleservice.infrastructure.helpers.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic adiClubTopic() {
        return TopicBuilder
                .name("adiClub")
                .build();
    }

    @Bean
    public NewTopic subscriptionTopic() {
        return TopicBuilder
                .name("priority")
                .build();
    }

    @Bean
    public NewTopic exceptionTopic() {
        return TopicBuilder
                .name("exception")
                .build();
    }
}
