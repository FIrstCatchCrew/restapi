package com.example.firstcatch_api.landing;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Landing {
    @Id
    private Long id;
    private String name;
    private String address;
}
