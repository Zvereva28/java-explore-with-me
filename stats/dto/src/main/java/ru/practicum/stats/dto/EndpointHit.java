package ru.practicum.stats.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EndpointHit {

    private Integer id;

    private String app;

    private String uri;

    private String ip;

    private String timestamp;

}
