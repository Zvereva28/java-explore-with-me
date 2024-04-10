package ru.practicum.ewm.server.models.compilation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewCompilationDto {

    private Boolean pinned;

    @NotBlank(message = "Не может быть пустым")
    @Size(min = 1, max = 50)
    private String title;

    private List<Long> events;
}
