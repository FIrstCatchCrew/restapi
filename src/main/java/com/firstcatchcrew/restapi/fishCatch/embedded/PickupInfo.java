package com.firstcatchcrew.restapi.fishCatch.embedded;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class PickupInfo {
    private String locationName;
    private String address;
    private LocalDateTime pickupTime;
}