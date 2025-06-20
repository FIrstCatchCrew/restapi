package com.firstcatchcrew.restapi.fishCatch.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class GeoLocation {

    @Column(name = "latitude")
    private double latitude;

    //CLEANUP: William fixed this for me; I had it marked as "latitude"
    @Column(name = "longitude")
    private double longitude;

    public GeoLocation() {
    }

    public GeoLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
