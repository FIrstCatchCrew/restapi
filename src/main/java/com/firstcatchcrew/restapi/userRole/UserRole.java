package com.firstcatchcrew.restapi.userRole;

import jakarta.persistence.*;

@Entity
public class UserRole {

    @Id
    @SequenceGenerator(name = "role_sequence", sequenceName = "role_sequence", allocationSize = 1)
    @GeneratedValue(generator = "role_sequence")

    private Long id;

    @Enumerated(EnumType.STRING)
    private UserRoleType type;

    private String label;
    private String description;

    public UserRole() {}

    public UserRole(UserRoleType type, String label, String description) {
        this.type = type;
        this.label = label;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public UserRoleType getType() { return type;};

    public void setType(UserRoleType type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
