package ru.practicum.ewm.server;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.practicum.stats.client.StatsClient;

@Configuration
public class EwnConfig {

    @Bean
    public StatsClient statsClient() {
        return new StatsClient("http://stats-server:9090", new RestTemplateBuilder());
    }
}

