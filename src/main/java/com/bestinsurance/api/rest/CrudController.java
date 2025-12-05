package com.bestinsurance.api.rest;

import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

public interface CrudController<CreateDTO, UpdateDTO, SearchDTO> {
    @PostMapping
    public SearchDTO create(@Valid @RequestBody CreateDTO customer);
    @GetMapping("/{id}")
    public SearchDTO searchById(@NotBlank @PathVariable String id);
    @GetMapping
    public List<SearchDTO> all();
    @PutMapping("/{id}")
    public SearchDTO update(@NotBlank @PathVariable String id, @Valid  @RequestBody UpdateDTO customer);
    @DeleteMapping("/{id}")
    public void delete(@NotBlank @PathVariable String id);
}
