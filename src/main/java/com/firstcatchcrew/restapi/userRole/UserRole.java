package com.firstcatchcrew.restapi.userRole;

import jakarta.persistence.*;

@Entity
public class UserRole {

    @Id
    @SequenceGenerator(name = "role_sequence", sequenceName = "role_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "role_sequence")

    private Long id;

    @Enumerated(EnumType.STRING)
    private UserRoleType type;

    private String label;        // CLEANUP: e.g., "Local Fisher"
    private String description;  // CLEANUP: e.g., "Can add and manage catches"

    public Long getId() {
        return id;
    }

    public UserRoleType getType() { return type;};

    public void setType(UserRoleType type) {
        this.type = type;
    }

    //ClEANUP: might need to do error handling here
    public void setTypeFromString(String role) {
        this.type = UserRoleType.valueOf(role);
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
