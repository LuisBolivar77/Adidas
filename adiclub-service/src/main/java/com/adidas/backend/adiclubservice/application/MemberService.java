package com.adidas.backend.adiclubservice.application;

import com.adidas.backend.adiclubservice.domain.Event;
import com.adidas.backend.adiclubservice.domain.EventType;
import com.adidas.backend.adiclubservice.domain.AdiClubMember;
import com.adidas.backend.adiclubservice.infrastructure.driven_adapter.gateways.PublisherGateway;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private static final Random RANDOM = new Random(System.nanoTime());
    private final PublisherGateway publisherGateway;

    @SneakyThrows
    public void getAdiClubMemberInfo(Event data) {
        var member = AdiClubMember
                .builder()
                .uuid(UUID.randomUUID().toString())
                .email(data.getData().toString())
                .points(RANDOM.nextInt(5000))
                .registrationDate(Instant.now().minus(RANDOM.nextInt(365), ChronoUnit.DAYS))
                .build();

        var memberSerialized = memberSerializer(member);

        var event = Event
                .builder()
                .id(UUID.randomUUID().toString())
                .type(EventType.UPDATED)
                .date(new Date())
                .data(memberSerialized)
                .build();

        publisherGateway.publishMemberPriority(event);
    }

    @SneakyThrows
    public String memberSerializer(AdiClubMember member) {
        var objectMapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        return objectMapper.writeValueAsString(member);
    }

    public void publishException(String email) {
        publisherGateway.publishException(
                Event.builder()
                        .id(UUID.randomUUID().toString())
                        .type(EventType.UPDATED)
                        .date(new Date())
                        .data(email)
                        .build()
        );
    }
}
