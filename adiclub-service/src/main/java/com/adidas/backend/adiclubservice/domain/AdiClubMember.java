package com.adidas.backend.adiclubservice.domain;

import lombok.*;
import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AdiClubMember {

  private String uuid;
  private String email;
  private Integer points;
  private Instant registrationDate;
}
