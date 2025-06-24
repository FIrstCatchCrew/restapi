package com.firstcatchcrew.restapi.person;

import com.firstcatchcrew.restapi.userRole.UserRole;
import com.firstcatchcrew.restapi.userRole.UserRoleService;
import com.firstcatchcrew.restapi.userRole.UserRoleType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/person")
@CrossOrigin
public class PersonController {
    private final PersonService personService;
    private final UserRoleService userRoleService;
    public PersonController(PersonService personService, UserRoleService userRoleService) {
        this.personService = personService;
        this.userRoleService = userRoleService;
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAll() {
        List<PersonDTO> dtos = personService.getAllPersons()
                .stream()
                .map(PersonMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getById(@PathVariable long id) {
        Person person = personService.getPersonById(id);
        return (person != null)
                ? ResponseEntity.ok(PersonMapper.toDto(person))
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO dto) {
        // lookup UserRole by type string
        UserRole role = userRoleService.getByType(UserRoleType.valueOf(dto.getRole()));
        if (role == null) return ResponseEntity.badRequest().build();

        Person person = PersonMapper.toEntity(dto, role);
        Person saved = personService.createPerson(person);
        return ResponseEntity.created(URI.create("/api/person/" + saved.getId()))
                .body(PersonMapper.toDto(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> update(@PathVariable long id, @RequestBody PersonDTO dto) {
        UserRole role = userRoleService.getByType(UserRoleType.valueOf(dto.getRole()));
        if (role == null) return ResponseEntity.badRequest().build();
        Person updated = personService.updatePerson(id, PersonMapper.toEntity(dto, role));
        return (updated != null)
                ? ResponseEntity.ok(PersonMapper.toDto(updated))
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        personService.deletePersonById(id);
        return ResponseEntity.noContent().build();
    }
}
