package ru.practicum.ewm.server;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.stats.dto.EndpointHit;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class EwnController {

    private final EwnService service;

    @PostMapping("/hit")
    public ResponseEntity<Void> saveStats(@RequestBody EndpointHit hit) {
        service.saveStats(hit);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/stats")
    public ResponseEntity<Object> getStats(
            @RequestParam(name = "start") String start,
            @RequestParam(name = "end") String end,
            @RequestParam(name = "unique", defaultValue = "false", required = false) Boolean unique,
            @RequestParam(name = "uris", required = false) @Nullable List<String> uris
    ) {
        return service.getStats(start, end, unique, uris);
    }
}
