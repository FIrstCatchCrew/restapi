package com.firstcatchcrew.restapi.fishCatch.embedded;

import jakarta.persistence.Embeddable;

@Embeddable
public class GeoLocation {
    private double latitude;
    private double longitude;
}
