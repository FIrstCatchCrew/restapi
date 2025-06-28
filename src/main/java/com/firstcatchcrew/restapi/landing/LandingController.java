package com.firstcatchcrew.restapi.landing;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/landing")
@CrossOrigin
public class LandingController {
    private final LandingService service;

    public LandingController(LandingService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Landing>> getAll() {
        return ResponseEntity.ok(service.getAllLandings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Landing> getById(@PathVariable("id") long landingId) {
        Landing l = service.getLandingById(landingId);
        return (l != null) ? ResponseEntity.ok(l) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Landing> create(@Validated @RequestBody Landing newLanding) {
        Landing saved = service.createLanding(newLanding);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Landing> update(@PathVariable("id") long landingId, @RequestBody Landing updated) {
        Landing result = service.updateLanding(landingId, updated);
        return (result != null) ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long landingId) {
        return service.deleteLandingById(landingId)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}