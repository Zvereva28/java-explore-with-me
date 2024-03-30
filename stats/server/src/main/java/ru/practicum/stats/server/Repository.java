package ru.practicum.stats.server;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.stats.dto.MetricDTO;

import java.time.LocalDateTime;
import java.util.List;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<Hit, Long> {

    @Query("select h.app as app, h.uri as uri, COUNT(h.ip) as hits " +
            "from Hit h " +
            "where h.timestamp between ?1 and ?2 " +
            "group by h.app, h.uri " +
            "order by COUNT(h.ip) desc")
    List<MetricDTO> findAllByTimestampWhereUrisIsNull(LocalDateTime start, LocalDateTime end);

    @Query("select h.app as app, h.uri as uri, COUNT(h.ip) as hits " +
            "from Hit h " +
            "where h.timestamp between ?1 and ?2 " +
            "and h.uri IN (?3) " +
            "group by h.app, h.uri " +
            "order by COUNT(h.ip) desc")
    List<MetricDTO> findAllByTimestampWhereUris(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query("select h.app as app, h.uri as uri, COUNT(DISTINCT h.ip) as hits " +
            "from Hit h " +
            "where h.timestamp between ?1 and ?2 " +
            "group by h.app, h.uri " +
            "order by COUNT(h.ip) desc")
    List<MetricDTO> findAllByTimestampWhereUrisIsNullAndUniqueTrue(LocalDateTime start, LocalDateTime end);

    @Query("select h.app as app, h.uri as uri, COUNT(DISTINCT h.ip) as hits " +
            "from Hit h " +
            "where h.timestamp between ?1 and ?2 " +
            "and h.uri IN (?3) " +
            "group by h.app, h.uri " +
            "order by COUNT(DISTINCT h.ip) desc")
    List<MetricDTO> findAllByTimestampWhereUrisAndUniqueTrue(LocalDateTime start, LocalDateTime end, List<String> uris);

}
