package com.adidas.backend.emailservice.domain;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    private String id;

    private Date date;

    private EventType type;

    private String data;
}
