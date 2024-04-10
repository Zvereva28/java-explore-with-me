package ru.practicum.ewm.server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.practicum.ewm.server.exceptions.ConflictException;
import ru.practicum.ewm.server.exceptions.NotFoundException;
import ru.practicum.ewm.server.mapper.RequestMapper;
import ru.practicum.ewm.server.models.event.Event;
import ru.practicum.ewm.server.models.event.EventState;
import ru.practicum.ewm.server.models.request.ParticipationRequest;
import ru.practicum.ewm.server.models.request.ParticipationRequestDto;
import ru.practicum.ewm.server.models.request.RequestStatus;
import ru.practicum.ewm.server.models.user.User;
import ru.practicum.ewm.server.service.RequestsService;
import ru.practicum.ewm.server.storage.EventRepository;
import ru.practicum.ewm.server.storage.RequestRepository;
import ru.practicum.ewm.server.storage.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Validated
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RequestsServiceImpl implements RequestsService {

    private final RequestRepository requestRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final RequestMapper requestMapper;

    @Override
    @Transactional
    public ParticipationRequestDto createRequest(Long userId, Long eventId) {
        getUserByIdOrElseThrow(userId);
        Event event = getEventByIdOrElseThrow(eventId);
        requestValidating(userId, eventId, event);

        ParticipationRequest request = new ParticipationRequest();
        request.setRequester(userId);
        request.setEvent(eventId);
        if (!event.getRequestModeration() || event.getParticipantLimit().equals(0)) {
            request.setStatus(RequestStatus.CONFIRMED);
            event.setConfirmedRequests(event.getConfirmedRequests() + 1);
        }
        requestRepository.save(request);

        log.info("Запрос пользователем с id={} на участие в событии с id={} создан", userId, eventId);
        return requestMapper.toRequestDto(request);
    }

    @Override
    public List<ParticipationRequestDto> getRequests(Long userId) {
        getUserByIdOrElseThrow(userId);
        List<ParticipationRequest> requests = requestRepository.findAllByRequester(userId);

        log.info("Получен список запросов пользователя с id={}", userId);
        return requests.stream().map(requestMapper::toRequestDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ParticipationRequestDto cancelRequest(Long userId, Long requestId) {
        getUserByIdOrElseThrow(userId);
        ParticipationRequest request = getRequestByIdOrElseThrow(requestId);
        request.setStatus(RequestStatus.CANCELED);

        log.info("Запрос пользователем с id={} на участие в событии с id={} отменен", userId, request.getEvent());
        return requestMapper.toRequestDto(request);
    }

    private Event getEventByIdOrElseThrow(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Event with id=%d was not found", id)));
    }

    private User getUserByIdOrElseThrow(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("User with id=%d was not found", id)));
    }

    private ParticipationRequest getRequestByIdOrElseThrow(Long id) {
        return requestRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Request with id=%d was not found", id)));
    }

    private void requestValidating(Long userId, Long eventId, Event event) {

        if (requestRepository.findByEventAndRequester(eventId, userId) != null) {
            throw new ConflictException("You can't add a repeat request");
        }

        if (event.getInitiator().getId().equals(userId)) {
            throw new ConflictException("Event initiator cannot add a request to participate in his event");
        }

        if (!event.getState().equals(EventState.PUBLISHED)) {
            throw new ConflictException("You cannot participate in an unpublished event");
        }

        if (event.getParticipantLimit() != 0) {
            if (event.getConfirmedRequests() >= event.getParticipantLimit()) {
                throw new ConflictException("Event has reached the limit of requests for participation");
            }
        }

    }
}
