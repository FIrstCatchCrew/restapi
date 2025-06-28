package com.firstcatchcrew.restapi.fisherProfile;

import com.firstcatchcrew.restapi.fishCatch.dto.CatchViewDTO;
import com.firstcatchcrew.restapi.fisherProfile.dto.FisherProfileCreateDTO;
import com.firstcatchcrew.restapi.fisherProfile.dto.FisherProfileViewDTO;
import com.firstcatchcrew.restapi.landing.Landing;
import com.firstcatchcrew.restapi.landing.LandingService;
import com.firstcatchcrew.restapi.person.Person;
import com.firstcatchcrew.restapi.person.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @GetMapping
    public ResponseEntity<List<FisherProfileViewDTO>> getAll() {
        return ResponseEntity.ok(fisherService.getAllFishers());
    }

    @GetMapping("/{id}/catches")
    public ResponseEntity<List<CatchViewDTO>> getCatchesByFisherId(@PathVariable long id) {
        return ResponseEntity.ok(fisherService.getCatchesByFisherId(id));
    }

    @GetMapping("/{id}/catches/expired")
    public ResponseEntity<List<CatchViewDTO>> getExpiredUnsoldCatchesByFisherId(@PathVariable long id) {
        List<CatchViewDTO> catches = fisherService.getExpiredUnsoldCatchesByFisherId(id);
        return ResponseEntity.ok(catches);
    }

    @GetMapping("/{id}/catches/sold")
    public ResponseEntity<List<CatchViewDTO>> getSoldCatchesByFisherId(@PathVariable long id) {
        return ResponseEntity.ok(fisherService.getSoldCatchesByFisherId(id));
    }


    @GetMapping("/{id}")
    public ResponseEntity<FisherProfileViewDTO> getById(@PathVariable long id) {
        FisherProfileViewDTO fisher = fisherService.getFisherById(id);
        return (fisher != null)
                ? ResponseEntity.ok(fisher)
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<FisherProfileViewDTO> create(@Validated @RequestBody FisherProfileCreateDTO dto) {
        Person person = personService.getPersonEntityById(dto.getPersonId());
        Landing landing = landingService.getLandingById(dto.getDefaultLandingId());

        if (person == null || landing == null) {
            return ResponseEntity.badRequest().build();
        }

        FisherProfile saved = fisherService.createFisher(
                FisherProfileMapper.toEntity(dto, person, landing)
        );

        FisherProfileViewDTO viewDto = FisherProfileMapper.from(saved);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(viewDto.getId())
                .toUri();

        return ResponseEntity.created(location).body(FisherProfileMapper.from(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FisherProfileViewDTO> update(@PathVariable long id, @RequestBody FisherProfileCreateDTO dto) {
        Person person = personService.getPersonEntityById(dto.getPersonId());
        Landing landing = landingService.getLandingById(dto.getDefaultLandingId());

        if (person == null || landing == null) {
            return ResponseEntity.badRequest().build();
        }

        FisherProfile updated = fisherService.updateFisher(
                id,
                FisherProfileMapper.toEntity(dto, person, landing)
        );

        return (updated != null)
                ? ResponseEntity.ok(FisherProfileMapper.from(updated))
                : ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        return fisherService.deleteFisherById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
