package ru.practicum.ewm.server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.practicum.ewm.server.exceptions.NotFoundException;
import ru.practicum.ewm.server.mapper.CompilationMapper;
import ru.practicum.ewm.server.mapper.EventMapper;
import ru.practicum.ewm.server.models.compilation.Compilation;
import ru.practicum.ewm.server.models.compilation.CompilationDto;
import ru.practicum.ewm.server.models.compilation.NewCompilationDto;
import ru.practicum.ewm.server.models.compilation.UpdateCompilationRequest;
import ru.practicum.ewm.server.models.event.Event;
import ru.practicum.ewm.server.service.CompilationService;
import ru.practicum.ewm.server.storage.CompilationRepository;
import ru.practicum.ewm.server.storage.EventRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Validated
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {
    private final CompilationRepository compilationRepository;
    private final EventRepository eventRepository;
    private final CompilationMapper compilationMapper;
    private final EventMapper eventMapper;

    @Override
    @Transactional
    public CompilationDto createCompilation(NewCompilationDto newCompilationDto) {

        Compilation compilation = compilationRepository
                .save(compilationMapper.newCompilationToCompilation(newCompilationDto));

        log.info("Создана подборка событий - {}", newCompilationDto.getTitle());
        return setEventsToCompilationDto(compilation);
    }

    @Override
    @Transactional
    public CompilationDto updateCompilation(Long compId, UpdateCompilationRequest updateCompilation) {
        Compilation compilation = getCompilationByIdOrElseThrow(compId);
        compilation.setTitle(
                updateCompilation.getTitle() != null ? updateCompilation.getTitle() : compilation.getTitle()
        );
        compilation.setPinned(
                updateCompilation.getPinned() != null ? updateCompilation.getPinned() : compilation.getPinned()
        );
        compilation.setEvents(
                updateCompilation.getEvents() != null ? updateCompilation.getEvents() : compilation.getEvents()
        );

        log.info("Подборка событий {} обновлена", compilation.getTitle());
        return setEventsToCompilationDto(compilation);
    }

    @Override
    public CompilationDto getCompilation(Long compId) {
        Compilation compilation = getCompilationByIdOrElseThrow(compId);

        log.info("Получена подборка событий - {}", compilation.getTitle());
        return setEventsToCompilationDto(compilation);
    }

    @Override
    @Transactional
    public void deleteCompilation(Long compId) {
        getCompilationByIdOrElseThrow(compId);
        compilationRepository.deleteById(compId);

        log.info("Подборка событий с id={} удалена", compId);
    }

    @Override
    public List<CompilationDto> getAllCompilations(Boolean pinned, Integer from, Integer size) {

        Pageable pageable = PageRequest.of(from, size);
        List<Compilation> compilations;
        if (pinned != null) {
            compilations = compilationRepository.findAllByPinned(pinned, pageable).toList();
        } else {
            compilations = compilationRepository.findAll(pageable).toList();
        }

        log.info("Получена подборка событий по заданным параметрам");
        return compilations.stream().map(this::setEventsToCompilationDto).collect(Collectors.toList());
    }

    private Compilation getCompilationByIdOrElseThrow(Long id) {
        return compilationRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Compilation with id=%d was not found", id)));
    }

    private CompilationDto setEventsToCompilationDto(Compilation compilation) {
        if (compilation.getEvents() != null) {
            List<Event> events = eventRepository.findAllByIdIn(compilation.getEvents())
                    .orElseThrow(() -> new NotFoundException("Event not found"));
            CompilationDto compilationDto = compilationMapper.toCompilationDto(compilation);
            compilationDto.setEvents(events.stream().map(eventMapper::toEventShort).collect(Collectors.toList()));
            return compilationDto;
        } else {
            return compilationMapper.toCompilationDto(compilation);
        }
    }
}

