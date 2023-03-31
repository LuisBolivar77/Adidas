package com.adidas.backend.adiclubservice.infrastructure.driven_adapter;

import com.adidas.backend.adiclubservice.application.MemberService;
import com.adidas.backend.adiclubservice.domain.Event;
import com.adidas.backend.adiclubservice.domain.ThreadLocalData;
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

    private final MemberService memberService;

    @Retryable(value = { ConfigException.class }, maxAttempts = 3, backoff = @Backoff(delay = 60000))
    @SneakyThrows
    @KafkaListener(
            topics = "adiClub",
            groupId = "groupId",
            containerFactory = "factory"
    )
    public void consumerAdiClub(Event event) {
        ThreadLocalData.setValue(event.getData());
        memberService.getAdiClubMemberInfo(event);
    }

}
