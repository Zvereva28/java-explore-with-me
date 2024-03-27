package ru.practicum.stats.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.stats.dto.EndpointHit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatsClient extends BaseClient {

    @Autowired
    public StatsClient(@Value("${stats-client.url}") String serverUrl, RestTemplateBuilder builder) {
        super(builder.uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build()
        );
    }

    public void createEndpointHit(EndpointHit hit) {
        post("/hit", hit);
    }

    public ResponseEntity<Object> getViewStats(String start, String end, Boolean unique, List<String> uris) {

        Map<String, Object> parameters;

        if (uris == null) {
            parameters = new HashMap<>();
            parameters.put("start", start);
            parameters.put("end", end);
            parameters.put("unique", unique);
            return get("/stats" + "?start={start}&end={end}&unique={unique}", parameters);
        } else {
            parameters = new HashMap<>();
            parameters.put("start", start);
            parameters.put("end", end);
            parameters.put("uris", uris);
            parameters.put("unique", unique);
            return get("/stats" + "?start={start}&end={end}&uris={uris}&unique={unique}", parameters);
        }
    }
}
