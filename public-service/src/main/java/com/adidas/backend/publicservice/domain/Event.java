package com.adidas.backend.publicservice.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Event {
    private String id;
    private Date date;
    private EventType type;
    private String data;
}
