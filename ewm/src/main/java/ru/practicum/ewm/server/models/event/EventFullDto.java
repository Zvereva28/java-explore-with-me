package ru.practicum.ewm.server.models.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.ewm.server.models.category.Category;
import ru.practicum.ewm.server.models.location.LocationDto;
import ru.practicum.ewm.server.models.user.UserShort;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventFullDto {

    private Long id;

    private String title;

    private String annotation;

    private Category category;

    private Boolean paid;

    private String eventDate;

    private UserShort initiator;

    private String description;

    private Integer participantLimit;

    private EventState state;

    private String createdOn;

    private LocationDto location;

    private Boolean requestModeration;

    private String publishedOn;

    private Integer confirmedRequests;

    private Integer views;

}

