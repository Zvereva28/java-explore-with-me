package ru.practicum.ewm.server.service;

import ru.practicum.ewm.server.models.category.Category;
import ru.practicum.ewm.server.models.category.CategoryDto;

import java.util.List;

public interface CategoriesService {
    Category createCategory(CategoryDto newCategory);

    Category updateCategory(Long catId, CategoryDto category);

    void deleteCategory(Long catId);

    Category getCategory(Long catId);

    List<Category> getAllCategories(Integer from, Integer size);
}
