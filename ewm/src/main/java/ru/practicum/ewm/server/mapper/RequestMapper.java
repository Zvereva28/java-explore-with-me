package ru.practicum.ewm.server.mapper;

import org.mapstruct.Mapper;
import ru.practicum.ewm.server.models.request.ParticipationRequest;
import ru.practicum.ewm.server.models.request.ParticipationRequestDto;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface RequestMapper {

    ParticipationRequest toRequest(ParticipationRequestDto dto);

    ParticipationRequestDto toRequestDto(ParticipationRequest request);
}
