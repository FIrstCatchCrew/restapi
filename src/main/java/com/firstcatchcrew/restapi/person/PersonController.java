package com.firstcatchcrew.restapi.person;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PersonController {

//    @GetMapping("/people")
//    public List<PersonDTO> getPeople() {
//        Scanner personService;
//        return personService.findAll().stream()
//                .map(p -> new PersonDTO(p.getId(), p.getUserame(), p.getRole().getLabel()))
//                .collect(Collectors.toList());
//    }
}
