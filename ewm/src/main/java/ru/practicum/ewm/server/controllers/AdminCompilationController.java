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
import ru.practicum.ewm.server.models.compilation.CompilationDto;
import ru.practicum.ewm.server.models.compilation.NewCompilationDto;
import ru.practicum.ewm.server.models.compilation.UpdateCompilationRequest;
import ru.practicum.ewm.server.service.CompilationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/compilations")
@RequiredArgsConstructor
@Validated
public class AdminCompilationController {

    private final CompilationService compilationService;

    @PostMapping
    public ResponseEntity<CompilationDto> createCompilation(@RequestBody @Valid NewCompilationDto compilationDto) {
        return ResponseEntity.status(201).body(compilationService.createCompilation(compilationDto));
    }

    @PatchMapping("/{compId}")
    public ResponseEntity<CompilationDto> createCompilation(
            @PathVariable Long compId,
            @RequestBody @Valid UpdateCompilationRequest updateCompilation) {
        return ResponseEntity.ok().body(compilationService.updateCompilation(compId, updateCompilation));
    }

    @DeleteMapping("/{compId}")
    public ResponseEntity<Void> deleteCompilation(@PathVariable Long compId) {
        compilationService.deleteCompilation(compId);
        return ResponseEntity.status(204).build();
    }
}
