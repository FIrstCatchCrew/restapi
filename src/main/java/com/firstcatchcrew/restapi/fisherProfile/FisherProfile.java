package com.firstcatchcrew.restapi.fisherProfile;

import com.firstcatchcrew.restapi.fishCatch.Catch;
import com.firstcatchcrew.restapi.landing.Landing;
import com.firstcatchcrew.restapi.person.Person;
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
