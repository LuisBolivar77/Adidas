package com.adidas.backend.publicservice.infrastructure.entry_point;

import com.adidas.backend.publicservice.application.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/saleSubscribe")
@RequiredArgsConstructor
public class SubscriptionRestController {

  private final EventService eventsService;

  @Value("${publicService.subscription.response}")
  private String subscriptionResponse;

  @Value("${publicService.exception.response}")
  private String exceptionResponse;

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleIOException(Exception ex) {
    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @GetMapping
  public ResponseEntity<String> getSubscriptionEndpointResponse(@RequestParam String email) {
    eventsService.eventCreator(email);
    return ResponseEntity
        .ok()
        .body(subscriptionResponse);
  }

}
