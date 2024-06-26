package ru.practicum.ewm.server.mapper;

import org.mapstruct.Mapper;
import ru.practicum.ewm.server.models.category.Category;
import ru.practicum.ewm.server.models.category.CategoryDto;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CategoryMapper {

    Category toCategory(CategoryDto categoryDto);

    CategoryDto toCategoryDto(Category category);
}