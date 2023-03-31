package com.adidas.backend.adiclubservice.domain;

import lombok.*;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Event {
    private String id;
    private Date date;
    private EventType type;
    private String data;
}
