package ru.practicum.ewm.server.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.practicum.ewm.server.models.location.Location;
import ru.practicum.ewm.server.models.location.LocationDto;

@Mapper
public interface LocationMapper {

    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    Location toLocation(LocationDto locationDto);

    LocationDto toLocationDto(Location location);
}
