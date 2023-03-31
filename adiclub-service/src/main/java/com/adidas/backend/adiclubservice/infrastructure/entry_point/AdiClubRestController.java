package com.adidas.backend.adiclubservice.infrastructure.entry_point;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import com.adidas.backend.adiclubservice.domain.AdiClubMember;

@RestController
@RequestMapping(value = "/adiclub")
public class AdiClubRestController {
  private static final Random RANDOM = new Random(System.nanoTime());

  @GetMapping
  public ResponseEntity<AdiClubMember> getAdiClubMemberInfo(
          @RequestParam(value = "emailAddress") final String emailAddress) {

    return ResponseEntity
        .ok()
        .body(AdiClubMember
            .builder()
            .email(emailAddress)
            .points(RANDOM.nextInt(5000))
            .registrationDate(Instant.now().minus(RANDOM.nextInt(365), ChronoUnit.DAYS))
            .build()
        );
  }
}
