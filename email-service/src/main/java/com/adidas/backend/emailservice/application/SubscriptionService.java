package com.adidas.backend.emailservice.application;

import com.adidas.backend.emailservice.domain.AdiClubMember;
import com.adidas.backend.emailservice.infrastructure.driven_adapter.gateway.EmailGateway;
import com.adidas.backend.emailservice.infrastructure.driven_adapter.gateway.SubscriptionGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class SubscriptionService {

    private final SubscriptionGateway subscriptionGateway;

    private final EmailGateway emailGateway;

    @Value("${email.subject}")
    private String subject;

    @Value("${email.text}")
    private String text;

    @Scheduled(fixedRate = 60000)
    public void selectWinner() {
        var orderSubscriptions = subscriptionGateway.getSubscriptionOrderedList();
        if (!orderSubscriptions.isEmpty()){
            var winner = orderSubscriptions.get(0);
            notifyUser(winner.getEmail());
            subscriptionGateway.deleteSubscription(winner);
        }
    }

    public void notifyUser(String email) {
        emailGateway.sendSimpleMessage(subject, text, email);
    }
}
