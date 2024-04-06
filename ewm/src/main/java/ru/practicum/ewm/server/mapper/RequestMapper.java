package ru.practicum.ewm.server.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.practicum.ewm.server.models.request.ParticipationRequest;
import ru.practicum.ewm.server.models.request.ParticipationRequestDto;

@Mapper
public interface RequestMapper {

    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

    ParticipationRequest toRequest(ParticipationRequestDto dto);

    ParticipationRequestDto toRequestDto(ParticipationRequest request);
}
