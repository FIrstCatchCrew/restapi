package com.firstcatchcrew.restapi.person;

import com.firstcatchcrew.restapi.userRole.UserRole;

public class PersonMapper {

    // Entity → DTO
    public static PersonDTO toDto(Person person) {
        PersonDTO dto = new PersonDTO();
        dto.setId(person.getId());
        dto.setUsername(person.getUsername());
        dto.setEmail(person.getEmail());
        dto.setRole(person.getRole().getType().name());// .getName() if it's an entity - changed DCE needed just type not role object
        return dto;
    }

    // DTO → Entity (useful when creating/updating a Person from a form)
    public static Person toEntity(PersonDTO dto, UserRole roleEntity) {
        Person person = new Person();
        person.setUsername(dto.getUsername());
        person.setEmail(dto.getEmail());
        person.setRole(roleEntity);
        return person;
    }
}
