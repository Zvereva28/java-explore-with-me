package ru.practicum.ewm.server.service;

import ru.practicum.ewm.server.models.category.CategoryDto;

import java.util.List;

public interface CategoriesService {

    CategoryDto createCategory(CategoryDto newCategory);

    CategoryDto updateCategory(Long catId, CategoryDto category);

    void deleteCategory(Long catId);

    CategoryDto getCategory(Long catId);

    List<CategoryDto> getAllCategories(Integer from, Integer size);
}
