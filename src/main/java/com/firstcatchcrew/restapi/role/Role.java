package com.firstcatchcrew.restapi.role;

import jakarta.persistence.*;

@Entity
public class Role {

    @Id
    @SequenceGenerator(name = "role_sequence", sequenceName = "role_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "role_sequence")


    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleType type;

    private String label;        // e.g., "Local Fisher"
    private String description;  // e.g., "Can add and manage catches"

    public RoleType getType() { return type;};

    // Constructors, getters, setters
}
