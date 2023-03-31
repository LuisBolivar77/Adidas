package com.adidas.backend.emailservice.infrastructure.entry_point;

import com.adidas.backend.emailservice.application.SubscriptionService;
import com.adidas.backend.emailservice.domain.ThreadLocalData;
import org.springframework.beans.factory.annotation.Autowired;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Autowired
    private SubscriptionService subscriptionService;

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        publishException();
        throw new RuntimeException(e.getMessage());
    }

    private void publishException() {
        subscriptionService.notifyUser(ThreadLocalData.getValue());
    }
}
