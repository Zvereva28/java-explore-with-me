package ru.practicum.ewm.server.models.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.ewm.server.models.category.Category;
import ru.practicum.ewm.server.models.user.UserShort;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventShortDto {

    private Long id;

    @NotBlank(message = "Не может быть пустым")
    private String title;

    @NotBlank(message = "Не может быть пустым")
    private String annotation;

    @NotBlank(message = "Не может быть пустым")
    private Category category;

    @NotBlank(message = "Не может быть пустым")
    private Boolean paid;

    @NotBlank(message = "Не может быть пустым")
    private String eventDate;

    private Integer confirmedRequests;

    @NotBlank(message = "Не может быть пустым")
    private UserShort initiator;

    private Integer views;
}
