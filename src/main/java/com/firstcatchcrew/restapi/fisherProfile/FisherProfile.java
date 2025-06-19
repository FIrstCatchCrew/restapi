package com.firstcatchcrew.restapi.fisherProfile;

import com.firstcatchcrew.restapi.fishCatch.Catch;
import com.firstcatchcrew.restapi.landing.Landing;
import com.firstcatchcrew.restapi.person.Person;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class FisherProfile {
    @Id
    @SequenceGenerator(name = "fisherProfile_sequence", sequenceName = "fisherProfile_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "fisherProfile_sequence")
    private Long id;

    @OneToOne
    @MapsId // CLEANUP: This tells JPA: "Use the Person's ID as the ID for this FisherProfile"
    private Person person;

    private String fishingLicenseNumber;

    @ManyToOne
    private Landing defaultLanding;

    @OneToMany(mappedBy = "fisher")
    private List<Catch> catches;


    public Long getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getFishingLicenseNumber() {
        return fishingLicenseNumber;
    }

    public void setFishingLicenseNumber(String fishingLicenseNumber) {
        this.fishingLicenseNumber = fishingLicenseNumber;
    }

    public Landing getDefaultLanding() {
        return defaultLanding;
    }

    public void setDefaultLanding(Landing defaultLanding) {
        this.defaultLanding = defaultLanding;
    }

    public List<Catch> getCatches() {
        return catches;
    }

    public void setCatches(List<Catch> catches) {
        this.catches = catches;
    }
}
