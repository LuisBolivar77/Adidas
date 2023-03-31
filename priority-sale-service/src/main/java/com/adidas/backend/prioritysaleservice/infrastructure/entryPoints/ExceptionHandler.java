package com.adidas.backend.prioritysaleservice.infrastructure.entryPoints;

import com.adidas.backend.prioritysaleservice.application.SubscriptionService;
import com.adidas.backend.prioritysaleservice.domain.ThreadLocalData;
import org.springframework.beans.factory.annotation.Autowired;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Autowired
    private SubscriptionService memberService;

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        publishException();
        throw new RuntimeException(e.getMessage());
    }

    private void publishException() {
        memberService.publishException(ThreadLocalData.getValue());
    }
}
