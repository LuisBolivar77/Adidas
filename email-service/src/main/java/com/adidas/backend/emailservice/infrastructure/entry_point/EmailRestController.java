package com.adidas.backend.emailservice.infrastructure.entry_point;

import com.adidas.backend.emailservice.application.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/notifiedAWinner")
@RequiredArgsConstructor
public class EmailRestController {

    private final SubscriptionService subscriptionService;

    @Value("${email.endpoint.response}")
    private String emailResponse;

    @GetMapping
    public ResponseEntity<String> getDummyEndpointResponse(@RequestParam String email) {
        subscriptionService.selectWinner();
        return ResponseEntity
                .ok()
                .body(emailResponse);
    }

}
