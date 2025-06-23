package com.firstcatchcrew.restapi.landing;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Landing {
    @Id
    @SequenceGenerator(name = "landing_sequence", sequenceName = "landing_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "landing_sequence")
    private Long landingId;

    private String name;
    private String address;

    public Long getId() {
        return landingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
