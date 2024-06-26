package ru.practicum.ewm.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.ewm.server.models.category.CategoryDto;
import ru.practicum.ewm.server.service.CategoriesService;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class PublicCategoriesController {

    private final CategoriesService service;

    @GetMapping("/{categoriesId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long categoriesId) {
        return ResponseEntity.ok().body(service.getCategory(categoriesId));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories(
            @RequestParam(name = "from", defaultValue = "0") @Min(0) Integer from,
            @RequestParam(name = "size", defaultValue = "10") @Min(1) @Max(100) Integer size) {
        return ResponseEntity.ok().body(service.getAllCategories(from, size));
    }

}


