package ru.practicum.ewm.server.mapper;

import org.mapstruct.Mapper;
import ru.practicum.ewm.server.models.location.Location;
import ru.practicum.ewm.server.models.location.LocationDto;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface LocationMapper {

    Location toLocation(LocationDto locationDto);

    LocationDto toLocationDto(Location location);
}
