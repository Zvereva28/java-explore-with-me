package ru.practicum.ewm.server.models.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.ewm.server.models.location.LocationDto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventUserRequest {

    @Size(min = 3, max = 120)
    private String title;

    @Size(min = 20, max = 2000)
    private String annotation;

    private Long category;

    @Size(min = 20, max = 7000)
    private String description;

    private String eventDate;

    private LocationDto location;

    private Boolean paid;

    @Min(0)
    private Integer participantLimit;

    private Boolean requestModeration;

    private StateAction stateAction;

}
