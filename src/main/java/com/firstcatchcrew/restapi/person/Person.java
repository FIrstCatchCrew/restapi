package com.firstcatchcrew.restapi.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.firstcatchcrew.restapi.userRole.UserRole;
import com.firstcatchcrew.restapi.userRole.UserRoleType;
import jakarta.persistence.*;

@Entity
@Table(
        name = "people",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"email"}),
                @UniqueConstraint(columnNames = {"username"})
        },
        indexes = {
                @Index(name = "idx_people_email", columnList = "email"),
                @Index(name = "idx_people_username", columnList = "username")
        }
)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @JsonIgnore
    @Column(name = "password_hash", nullable = false, length = 100)
    private String passwordHash;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private UserRole role;

    @Column(nullable = false)
    private boolean enabled = true;

    public Person() {}  //Added for Jackson, which requires it to deserialize

    @PrePersist
    @PreUpdate
    private void normalize() {
        if (email != null) email = email.trim().toLowerCase();
        if (username != null) username = username.trim();
    }

    public long getId() {
        return id;
    }

    public UserRole getRole() {
        return role;
    }
    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPassword() {
        return passwordHash;
    }
    public void setPassword(String password) {
        this.passwordHash = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public UserRoleType getRoleType() {
        return role.getType();
    }

}
