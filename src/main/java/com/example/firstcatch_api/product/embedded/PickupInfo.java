package com.example.firstcatch_api.product.embedded;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class PickupInfo {
    private String locationName;
    private String address;
    private LocalDateTime pickupTime;
}