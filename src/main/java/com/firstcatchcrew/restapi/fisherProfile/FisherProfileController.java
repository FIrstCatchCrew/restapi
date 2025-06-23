package com.firstcatchcrew.restapi.fisherProfile;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/fisher")
@CrossOrigin
public class FisherProfileController {

    private final FisherProfileService fisherService;

    public FisherProfileController(FisherProfileService fisherService) {
        this.fisherService = fisherService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FisherProfile> getFisherById(@PathVariable long id) {
        FisherProfile fisher = fisherService.getFisherById(id);
        return (fisher != null) ? ResponseEntity.ok(fisher) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<FisherProfile> createFisher(@RequestBody FisherProfile newFisher) {
        FisherProfile saved = fisherService.createFisher(newFisher);
        URI location = URI.create("/api/fishers/" + saved.getId());
        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FisherProfile> updateFisher(@PathVariable long id, @RequestBody FisherProfile updatedFisher) {
        FisherProfile updated = fisherService.updateFisher(id, updatedFisher);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFisher(@PathVariable long id) {
        fisherService.deleteFisherById(id);
        return ResponseEntity.noContent().build();
    }
}
