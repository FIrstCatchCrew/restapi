package com.example.firstcatch_api.species;

import jakarta.persistence.*;

@Entity
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    //image, external link to information about species, alternative names?


}
