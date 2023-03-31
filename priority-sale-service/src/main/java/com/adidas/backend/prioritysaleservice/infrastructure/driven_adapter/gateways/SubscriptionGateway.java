package com.adidas.backend.prioritysaleservice.infrastructure.driven_adapter.gateways;

import com.adidas.backend.prioritysaleservice.domain.AdiClubMember;

import java.util.List;

public interface SubscriptionGateway {

    void saveSubscription(AdiClubMember subscription);
}
