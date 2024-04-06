package ru.practicum.ewm.server.service;


import ru.practicum.ewm.server.models.event.EventDto;
import ru.practicum.ewm.server.models.event.EventFullDto;
import ru.practicum.ewm.server.models.event.EventShortDto;
import ru.practicum.ewm.server.models.event.UpdateEventAdminRequest;
import ru.practicum.ewm.server.models.event.UpdateEventUserRequest;
import ru.practicum.ewm.server.models.request.EventRequestStatusUpdateRequest;
import ru.practicum.ewm.server.models.request.EventRequestStatusUpdateResult;
import ru.practicum.ewm.server.models.request.ParticipationRequestDto;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

public interface EventService {
    EventFullDto createEvent(Long userId, @Valid EventDto eventDto);

    List<EventShortDto> getAllEvents(Long userId, Integer from, Integer size);

    EventFullDto getEvent(Long userId, Long eventId);

    EventFullDto updateEvent(Long userId, Long eventId, EventDto eventDto);

    List<EventFullDto> getAdminAllEvents(Map<String, String> queryParams, Integer from, Integer size);

    EventFullDto updateEventByUser(Long userId, Long eventId, UpdateEventUserRequest eventDto);

    EventFullDto updateEventByAdmin(Long eventId, UpdateEventAdminRequest eventDto);

    List<ParticipationRequestDto> getRequests(Long userId, Long eventId);

    EventRequestStatusUpdateResult updateRequest(
            Long userId, Long eventIds, EventRequestStatusUpdateRequest updateRequest1);

    EventFullDto getPublicEvent(Long id, HttpServletRequest request);

    List<EventShortDto> getPublicAllEvents(Map<String, String> queryParams, Integer from, Integer size, HttpServletRequest request);


}
