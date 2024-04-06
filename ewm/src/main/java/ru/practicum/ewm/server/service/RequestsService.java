package ru.practicum.ewm.server.service;

import ru.practicum.ewm.server.models.request.ParticipationRequestDto;

import java.util.List;

public interface RequestsService {

    ParticipationRequestDto createRequest(Long userId, Long eventId);

    List<ParticipationRequestDto> getRequests(Long userId);

    ParticipationRequestDto cancelRequest(Long userId, Long requestId);
}
