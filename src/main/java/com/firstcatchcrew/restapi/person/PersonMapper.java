package com.firstcatchcrew.restapi.person;

import com.firstcatchcrew.restapi.userRole.UserRole;

public class PersonMapper {

    // Entity → DTO
    public static PersonDTO toDto(Person person) {
        PersonDTO dto = new PersonDTO();
        dto.setId(person.getId());
        dto.setUsername(person.getUsername());
        dto.setEmail(person.getEmail());
        dto.setRole(person.getRole().toString()); // .getName() if it's an entity
        return dto;
    }

    // DTO → Entity (useful when creating/updating a Person from a form)
    public static Person toEntity(PersonDTO dto) {
        Person person = new Person();
        person.setId(dto.getId());
        person.setUsername(dto.getUsername());
        person.setEmail(dto.getEmail());
        UserRole userRole = new UserRole();
        userRole.setTypeFromString(dto.getRole());
        person.setRole(userRole);
        return person;
    }
}
