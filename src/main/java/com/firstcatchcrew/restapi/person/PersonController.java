package com.firstcatchcrew.restapi.person;

import com.firstcatchcrew.restapi.userRole.UserRoleType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonDTO dto, @RequestParam String role) {
            PersonDTO created = personService.createPerson(dto, role);
            URI location = URI.create("/api/person/" + created.getId());
            return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> updatePerson(
            @PathVariable long id,
            @RequestBody PersonDTO dto) {
        PersonDTO updatedPerson = personService.updatePerson(id, dto);
        return (updatedPerson != null) ? ResponseEntity.ok(updatedPerson) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonById(@PathVariable long id) {
        return personService.deletePersonById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
