package com.example.firstcatch_api.fishCatch.embedded;

import jakarta.persistence.Embeddable;

@Embeddable
public class GeoLocation {
    private double latitude;
    private double longitude;
}
