package ru.practicum.stats.server;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.stats.dto.EndpointHit;
import ru.practicum.stats.dto.MetricDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HitService {
    private final Repository repository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final HitMapper hitMapper;

    @Transactional
    public EndpointHit createHit(EndpointHit hitDto) {
        Hit hit = repository.save(hitMapper.toHit(hitDto));
        log.info("Новая запись о запросе: '{}'", hit);
        return hitMapper.toHitDto(hit);
    }

    public List<MetricDTO> getStats(String start, String end, Boolean unique, List<String> uris) {
        LocalDateTime startDate = LocalDateTime.parse(start, formatter);
        LocalDateTime endDate = LocalDateTime.parse(end, formatter);
        List<MetricDTO> results;
        if (uris == null) {
            results = getHitsIfUrisIsNull(startDate, endDate, unique);
        } else {
            results = getHitsIfUris(startDate, endDate, unique, uris);
        }
        log.info("Статистика для uris: '{}'", uris);
        return results;
    }

    private List<MetricDTO> getHitsIfUrisIsNull(LocalDateTime start, LocalDateTime end, Boolean unique) {
        if (unique) {
            return repository.findAllByTimestampWhereUrisIsNullAndUniqueTrue(start, end);
        } else {
            return repository.findAllByTimestampWhereUrisIsNull(start, end);
        }
    }

    private List<MetricDTO> getHitsIfUris(LocalDateTime start, LocalDateTime end, Boolean unique, List<String> uris) {
        if (!unique) {
            return repository.findAllByTimestampWhereUris(start, end, uris);
        } else {
            return repository.findAllByTimestampWhereUrisAndUniqueTrue(start, end, uris);
        }
    }
}
