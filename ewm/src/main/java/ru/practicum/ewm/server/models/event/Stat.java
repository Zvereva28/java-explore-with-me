package ru.practicum.ewm.server.models.event;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Stat {
    private String app;
    private String uri;
    private Long hits;
}
