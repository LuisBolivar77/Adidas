package com.adidas.backend.prioritysaleservice.infrastructure.driven_adapter;

import com.adidas.backend.prioritysaleservice.domain.AdiClubMember;
import com.adidas.backend.prioritysaleservice.infrastructure.driven_adapter.gateways.SubscriptionGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SubscriptionRepository implements SubscriptionGateway {

    private final RedisTemplate<String, AdiClubMember> redisTemplate;

    private static final String KEY = "MEMBERS";

    @Override
    public void saveSubscription(AdiClubMember subscription) {
        redisTemplate.opsForSet().add(KEY, subscription);
    }
}
