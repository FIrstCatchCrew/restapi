package com.firstcatchcrew.restapi.species;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/species")
@CrossOrigin
public class SpeciesController {
    private final SpeciesService service;

    public SpeciesController(SpeciesService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Species>> getAll() {
        return ResponseEntity.ok(service.getAllSpecies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Species> getById(@PathVariable long id) {
        Species s = service.getSpeciesById(id);
        return (s != null) ? ResponseEntity.ok(s) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Species> create(@RequestBody Species newSpecies) {
        Species saved = service.createSpecies(newSpecies);
        return ResponseEntity.created(URI.create("/api/species/" + saved.getSpeciesId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Species> update(@PathVariable long id, @RequestBody Species updated) {
        Species result = service.updateSpecies(id, updated);
        return (result != null) ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        boolean deleted = service.deleteSpeciesById(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}