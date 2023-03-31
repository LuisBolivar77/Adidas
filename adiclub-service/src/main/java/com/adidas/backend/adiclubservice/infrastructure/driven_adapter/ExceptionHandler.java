package com.adidas.backend.adiclubservice.infrastructure.driven_adapter;

import com.adidas.backend.adiclubservice.application.MemberService;
import com.adidas.backend.adiclubservice.domain.ThreadLocalData;
import org.springframework.beans.factory.annotation.Autowired;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Autowired
    private MemberService memberService;

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        publishException();
        throw new RuntimeException(e.getMessage());
    }

    private void publishException() {
        memberService.publishException(ThreadLocalData.getValue());
    }
}
