package ru.practicum.ewm.server.models.comment;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateCommentRequest {
    @NotBlank
    @Size(min = 1, max = 4000)
    private String text;

}
