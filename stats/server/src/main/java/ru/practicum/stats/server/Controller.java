package ru.practicum.stats.server;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.stats.dto.EndpointHit;
import ru.practicum.stats.dto.ViewStats;
import ru.practicum.stats.server.service.HitService;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class Controller {
    private final HitService hitService;

    @PostMapping("/hit")
    public ResponseEntity<Void> createHit(@RequestBody EndpointHit hitDto) {
        hitService.createHit(hitDto);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/stats")
    public ResponseEntity<List<ViewStats>> getStats(
            @RequestParam(name = "start") String start,
            @RequestParam(name = "end") String end,
            @RequestParam(name = "unique", defaultValue = "false", required = false) Boolean unique,
            @RequestParam(name = "uris", required = false) List<String> uris
    ) {
        return ResponseEntity.ok().body(hitService.getStats(start, end, unique, uris));
    }
}

