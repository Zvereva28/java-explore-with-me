package ru.practicum.ewm.server.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import ru.practicum.ewm.server.models.category.Category;
import ru.practicum.ewm.server.models.event.Event;
import ru.practicum.ewm.server.models.event.EventDto;
import ru.practicum.ewm.server.models.event.EventFullDto;
import ru.practicum.ewm.server.models.event.EventShortDto;
import ru.practicum.ewm.server.models.event.UpdateEventAdminRequest;
import ru.practicum.ewm.server.models.event.UpdateEventUserRequest;

@Mapper
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mapping(target = "eventDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    Event dtoToEvent(EventDto eventDto);


    @Mapping(target = "eventDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Event eventUserUpdate(UpdateEventUserRequest newEvent, @MappingTarget Event event);


    @Mapping(target = "eventDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Event eventAdminUpdate(UpdateEventAdminRequest newEvent, @MappingTarget Event event);

    Category map(Long categoryId);

    @Mapping(target = "eventDate", expression = "java(DateTimeFormatter.ofPattern(\"yyyy-MM-dd HH:mm:ss\").format(event.getEventDate()))")
    @Mapping(target = "createdOn", expression = "java(DateTimeFormatter.ofPattern(\"yyyy-MM-dd HH:mm:ss\").format(event.getCreatedOn()))")
    @Mapping(target = "publishedOn", expression = "java(DateTimeFormatter.ofPattern(\"yyyy-MM-dd HH:mm:ss\").format(event.getCreatedOn()))")
    EventFullDto toEventFullDto(Event event);

    @Mapping(target = "eventDate", expression = "java(DateTimeFormatter.ofPattern(\"yyyy-MM-dd HH:mm:ss\").format(event.getEventDate()))")
    EventShortDto toEventShort(Event event);
}
