package com.firstcatchcrew.restapi.person;

public class PersonMapper {

    // Entity → DTO
    public static PersonDTO toDto(Person person) {
        PersonDTO dto = new PersonDTO();
        dto.setId(person.getId());
        dto.setName(person.getName());
        dto.setEmail(person.getEmail());
        dto.setRole(person.getRole().toString()); // .getName() if it's an entity
        return dto;
    }

    // DTO → Entity (useful when creating/updating a Person from a form)
    public static Person toEntity(PersonDTO dto) {
        Person person = new Person();
        person.setId(dto.getId());
        person.setName(dto.getName());
        person.setEmail(dto.getEmail());
        // Assuming Role is an enum here
        Role role = new Role();
        role.setName(dto.getRole());
        person.setRole(role);
    }
}
