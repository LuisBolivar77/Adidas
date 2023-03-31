package com.adidas.backend.emailservice.infrastructure.driven_adapter.gateway;

public interface EmailGateway {
    void sendSimpleMessage(String subject, String text, String member);
}
