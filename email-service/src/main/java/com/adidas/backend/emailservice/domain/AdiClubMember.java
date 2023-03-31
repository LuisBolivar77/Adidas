package com.adidas.backend.emailservice.domain;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.Instant;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@RedisHash("AdiClubMember")
public class AdiClubMember implements Serializable {

    private String uuid;
    private String email;
    private Integer points;
    private Instant registrationDate;

}
