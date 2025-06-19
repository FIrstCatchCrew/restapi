package com.example.firstcatch_api.fisherProfile;

import com.example.firstcatch_api.fishCatch.Catch;
import com.example.firstcatch_api.landing.Landing;
import com.example.firstcatch_api.person.Person;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class FisherProfile {
    @Id
    private Long id;

    @OneToOne
    @MapsId // CLEANUP: This tells JPA: "Use the Person's ID as the ID for this FisherProfile"
    private Person person;

    private String fishingLicenseNumber;

    @ManyToOne
    private Landing defaultLanding;

    @OneToMany(mappedBy = "fisher")
    private List<Catch> catches;

}
