package ru.practicum.ewm.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.server.models.category.Category;
import ru.practicum.ewm.server.models.category.CategoryDto;
import ru.practicum.ewm.server.service.CategoriesService;

import javax.validation.Valid;


@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
@Validated
public class AdminCategoriesController {

    private final CategoriesService categoriesService;

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody @Valid CategoryDto category) {
        return ResponseEntity.status(201).body(categoriesService.createCategory(category));
    }

    @PatchMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(
            @RequestBody @Valid CategoryDto category,
            @PathVariable Long categoryId) {
        return ResponseEntity.ok().body(categoriesService.updateCategory(categoryId, category));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> delCategory(@PathVariable Long categoryId) {
        categoriesService.deleteCategory(categoryId);
        return ResponseEntity.status(204).build();
    }

}

