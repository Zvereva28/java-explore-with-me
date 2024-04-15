package ru.practicum.ewm.server.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.ewm.server.models.location.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
