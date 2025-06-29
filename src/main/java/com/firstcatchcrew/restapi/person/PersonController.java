package com.firstcatchcrew.restapi.person;

import com.firstcatchcrew.restapi.userRole.UserRoleType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/person")
@CrossOrigin
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAll() {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @GetMapping("/roles")
    public ResponseEntity<List<PersonDTO>> getAllByRoleType(@RequestParam String role) {
        return ResponseEntity.ok(personService.getPersonByRoleType(role));
    }

    @GetMapping("/{id}/role")
    public ResponseEntity<UserRoleType> getRoleTypeByPersonID(@PathVariable long id) {
        return ResponseEntity.ok(personService.getRoleTypeByPersonId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getById(@PathVariable long id) {
        PersonDTO dto = personService.getPersonById(id);
        return (dto == null)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(dto);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<PersonDTO> getByUsername(@PathVariable String username) {
        PersonDTO dto = personService.getByUsername(username);
        return (dto == null)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<PersonDTO> login(@RequestBody LoginDTO credentials) {
        Person person = personService.authenticate(credentials.getEmail(), credentials.getPassword());
        return (person != null)
                ? ResponseEntity.ok(PersonMapper.toDto(person))
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


    @PostMapping
    public ResponseEntity<PersonDTO> create(@Validated @RequestBody PersonDTO dto) {
            PersonDTO saved = personService.createPerson(dto);

            URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> update(
            @PathVariable long id,
            @RequestBody PersonDTO dto) {
        PersonDTO updatedPerson = personService.updatePerson(id, dto);
        return (updatedPerson != null) ? ResponseEntity.ok(updatedPerson) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        return personService.deletePersonById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
