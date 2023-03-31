package com.adidas.backend.emailservice.infrastructure.driven_adapter;

import com.adidas.backend.emailservice.domain.AdiClubMember;
import com.adidas.backend.emailservice.infrastructure.driven_adapter.gateway.SubscriptionGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.SortParameters;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.query.SortQueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class SubscriptionRepository implements SubscriptionGateway {

    private final RedisTemplate<String, AdiClubMember> redisTemplate;

    private static final String KEY = "MEMBERS";

    @Override
    public void deleteSubscription(AdiClubMember member) {
        redisTemplate.opsForSet().remove(KEY, member);
    }

    @Override
    public List<AdiClubMember> getSubscriptionOrderedList() {
        try {
            SortQueryBuilder<String> builder = SortQueryBuilder.sort(KEY);
            builder.by("object:*->points").order(SortParameters.Order.ASC);
            return redisTemplate.sort(builder.build());
        } catch (Exception e) {
            return null;
        }
    }


}
