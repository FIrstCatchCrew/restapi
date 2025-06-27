package com.firstcatchcrew.restapi.person;

import com.firstcatchcrew.restapi.userRole.UserRole;
import com.firstcatchcrew.restapi.userRole.UserRoleRepository;
import com.firstcatchcrew.restapi.userRole.UserRoleType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final UserRoleRepository userRoleRepository;

    public PersonService(PersonRepository personRepository, UserRoleRepository userRoleRepository) {
        this.personRepository = personRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public List<PersonDTO> getAllPersons() {
        return personRepository.findAll()
                .stream()
                .map(PersonMapper::toDto)
                .toList();
    }

    public PersonDTO getPersonById(long id) {
        return personRepository.findById(id)
                .map(PersonMapper::toDto)
                .orElse(null);
    }

    public List<PersonDTO> getPersonByRoleType(String role) {
        UserRoleType type = UserRoleType.valueOf(role.toUpperCase());
        UserRole roleEntity = userRoleRepository.findByType(type);
        return personRepository.findByRole(roleEntity)
                .stream()
                .map(PersonMapper::toDto)
                .toList();
    }

    public UserRoleType getRoleTypeByPersonId(long personId) {
        return personRepository.findById(personId)
                .map(person -> person.getRole().getType())
                .orElseThrow(() -> new IllegalArgumentException("Person not found"));
    }

    @Transactional
    public PersonDTO createPerson(PersonDTO newPersonDto, String role) {
        UserRoleType type = UserRoleType.valueOf(role.toUpperCase());
        UserRole roleEntity = userRoleRepository.findByType(type);
        Person newPerson = PersonMapper.toEntity(newPersonDto, roleEntity);
        Person savedPerson = personRepository.save(newPerson);
        return PersonMapper.toDto(savedPerson);
    }


    @Transactional
    public PersonDTO updatePerson(long id, PersonDTO updatedPerson) {
        return personRepository.findById(id)
                .map(existing -> {
                    existing.setUsername(updatedPerson.getUsername());
                    existing.setEmail(updatedPerson.getEmail());
                    // role update would require fetching UserRole from repo
                    return PersonMapper.toDto(personRepository.save(existing));
                })
                .orElse(null);
    }


    public boolean deletePersonById(long id) {
        if (!personRepository.existsById(id)) return false;
        personRepository.deleteById(id);
        return true;
    }

    public Person getPersonEntityById(long id) {
        return personRepository.findById(id).orElse(null);
    }
}
