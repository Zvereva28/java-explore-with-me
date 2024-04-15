package ru.practicum.ewm.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.server.models.event.EventFullDto;
import ru.practicum.ewm.server.models.event.EventShortDto;
import ru.practicum.ewm.server.service.EventService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class PublicEventController {
    private final EventService eventService;

    @GetMapping("/{eventId}")
    public ResponseEntity<EventFullDto> getEvent(@PathVariable Long eventId, HttpServletRequest request) {
        return ResponseEntity.ok().body(eventService.getPublicEvent(eventId, request));
    }

    @GetMapping
    public ResponseEntity<List<EventShortDto>> getAllEvents(
            @RequestParam Map<String, String> queryParams,
            @RequestParam(name = "from", defaultValue = "0", required = false) @Min(0) Integer from,
            @RequestParam(name = "size", defaultValue = "10", required = false) @Min(1) @Max(1000) Integer size,
            HttpServletRequest request) {

        return ResponseEntity.ok().body(eventService.getPublicAllEvents(queryParams, from, size, request));
    }
}
