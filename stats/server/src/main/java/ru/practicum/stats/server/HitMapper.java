package ru.practicum.stats.server;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.practicum.stats.dto.EndpointHit;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface HitMapper {

    @Mapping(source = "timestamp", target = "timestamp", dateFormat = "yyyy-MM-dd HH:mm:ss")
    EndpointHit toHitDto(Hit hit);

    @Mapping(source = "timestamp", target = "timestamp", dateFormat = "yyyy-MM-dd HH:mm:ss")
    Hit toHit(EndpointHit hitDto);

}
