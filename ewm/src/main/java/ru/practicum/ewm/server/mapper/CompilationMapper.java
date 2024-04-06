package ru.practicum.ewm.server.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import ru.practicum.ewm.server.models.compilation.Compilation;
import ru.practicum.ewm.server.models.compilation.CompilationDto;
import ru.practicum.ewm.server.models.compilation.NewCompilationDto;

@Mapper
public interface CompilationMapper {
    CompilationMapper INSTANCE = Mappers.getMapper(CompilationMapper.class);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    Compilation newCompilationToCompilation(NewCompilationDto dto);

    @Mapping(source = "events", target = "events", ignore = true)
    CompilationDto toCompilationDto(Compilation compilation);

}