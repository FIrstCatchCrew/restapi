package com.firstcatchcrew.restapi.landing;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<Landing> create(@RequestBody Landing newLanding) {
        Landing saved = service.createLanding(newLanding);
        return ResponseEntity.created(URI.create("/api/landings/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Landing> update(@PathVariable("id") long landingId, @RequestBody Landing updated) {
        Landing result = service.updateLanding(landingId, updated);
        return (result != null) ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") long landingId) {
        service.deleteLandingById(landingId);
        return ResponseEntity.noContent().build();
    }
}