package com.adidas.backend.emailservice.infrastructure.driven_adapter.gateway;

import com.adidas.backend.emailservice.domain.AdiClubMember;
import java.util.List;

public interface SubscriptionGateway {

    void deleteSubscription(AdiClubMember member);
    List<AdiClubMember> getSubscriptionOrderedList();

}
