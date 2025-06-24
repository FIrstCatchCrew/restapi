package com.firstcatchcrew.restapi.fisherProfile;

import com.firstcatchcrew.restapi.landing.Landing;
import com.firstcatchcrew.restapi.landing.LandingService;
import com.firstcatchcrew.restapi.person.Person;
import com.firstcatchcrew.restapi.person.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/fisher")
@CrossOrigin
public class FisherProfileController {

    private final FisherProfileService fisherService;
    private final PersonService personService;
    private final LandingService landingService;

    public FisherProfileController(FisherProfileService fisherService, PersonService personService, LandingService landingService) {
        this.fisherService = fisherService;
        this.personService = personService;
        this.landingService = landingService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FisherProfile> getFisherById(@PathVariable long id) {
        FisherProfile fisher = fisherService.getFisherById(id);
        return (fisher != null) ? ResponseEntity.ok(fisher) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<FisherProfile> createFisher(@RequestBody FisherProfileDTO dto) {
        Person person = personService.getPersonById(dto.getPersonId());
        Landing landing = landingService.getLandingById(dto.getDefaultLandingId());

        if (person == null || landing == null) {
            return ResponseEntity.badRequest().build();
        }

        FisherProfile profile = new FisherProfile();
        profile.setPerson(person);
        profile.setFishingLicenseNumber(dto.getFishingLicenseNumber());
        profile.setDefaultLanding(landing);

        FisherProfile saved = fisherService.createFisher(profile);
        URI location = URI.create("/api/fisher/" + saved.getId());
        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FisherProfile> updateFisher(@PathVariable long id, @RequestBody FisherProfileDTO dto) {
        Person person = personService.getPersonById(dto.getPersonId());
        Landing landing = landingService.getLandingById(dto.getDefaultLandingId());

        if (person == null || landing == null) {
            return ResponseEntity.badRequest().build();
        }

        FisherProfile updatedProfile = new FisherProfile();
        updatedProfile.setPerson(person);
        updatedProfile.setFishingLicenseNumber(dto.getFishingLicenseNumber());
        updatedProfile.setDefaultLanding(landing);

        FisherProfile updated = fisherService.updateFisher(id, updatedProfile);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFisher(@PathVariable long id) {
        fisherService.deleteFisherById(id);
        return ResponseEntity.noContent().build();
    }
}
