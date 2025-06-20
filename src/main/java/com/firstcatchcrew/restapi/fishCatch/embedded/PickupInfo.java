package com.firstcatchcrew.restapi.fishCatch.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

//CLEANUP: we might want to automate pickup time

@Embeddable
public class PickupInfo {

    @Column(name = "pickup_location_name")
    private String locationName;

    @Column(name = "pickup_address")

    private String address;
    @Column(name = "pickup_time")
    private LocalDateTime pickupTime;

    public PickupInfo() {
    }

    public PickupInfo(String locationName, String address, LocalDateTime pickupTime) {
        this.locationName = locationName;
        this.address = address;
        this.pickupTime = pickupTime;
    }
    public String getLocationName() {
        return locationName;
    }
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public LocalDateTime getPickupTime() {
        return pickupTime;
    }
    public void setPickupTime(LocalDateTime pickupTime) {
        this.pickupTime = pickupTime;
    }
}