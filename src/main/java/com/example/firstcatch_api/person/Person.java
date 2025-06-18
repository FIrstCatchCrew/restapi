package com.example.firstcatch_api.person;

import com.example.firstcatch_api.role.Role;
import com.example.firstcatch_api.role.RoleType;
import jakarta.persistence.*;


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


    public RoleType getRoleType() {
        return role.getType();
    }
    // other contact information
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

}
