package com.firstcatchcrew.restapi.person;

import com.firstcatchcrew.restapi.role.Role;
import com.firstcatchcrew.restapi.role.RoleType;
import jakarta.persistence.*;


// CLEANUP: other contact information
//    private String firstName;
//    private String lastName;
//    private String phoneNumber;
//    private String address;
//    private String city;
//    private String province;
//    private String postalCode;
//    private String country;
//    private Calendar birthday;
//    private String profilePicture;

@Entity
public class Person {

    @Id
    @SequenceGenerator(name = "person_sequence", sequenceName = "person_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "person_sequence")
    private long id;

    private String username;
    private String email;
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public RoleType getRoleType() {
        return role.getType();
    }
    

}
