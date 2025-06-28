package com.firstcatchcrew.restapi.fishCatch.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CatchViewDTO {

    private Long id;
    private String speciesName;
    private BigDecimal quantityInKg;
    private BigDecimal price;
    private boolean available;

    //CLEANUP: catch info
    private String fisherName;
    private LocalDateTime timeStamp;
    private double latitude;
    private double longitude;

    //CLEANUP: pickup info
    private String landingName;
    private String pickupInstructions;
    private LocalDateTime pickupTime;


    public Long getId() {
        return id;
    }
    public void setId(Long id) { this.id = id; }

    public String getSpeciesName() {
        return speciesName;
    }
    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public BigDecimal getQuantityInKg() {
        return quantityInKg;
    }
    public void setQuantityInKg(BigDecimal quantityInKg) {
        this.quantityInKg = quantityInKg;
    }

    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getFisherName() {
        return fisherName;
    }
    public void setFisherName(String fisherName) {
        this.fisherName = fisherName;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
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

    public String getPickupLocationName() {
        return landingName;
    }
    public void setPickupLocationName(String pickupLocationName) {
        this.landingName = pickupLocationName;
    }

    @JsonProperty("pickup_instructions")
    public String getPickupInstructions() {
        return pickupInstructions;
    }
    public void setPickupInstructions(String pickupInstructions) {
        this.pickupInstructions = pickupInstructions;
    }

    public LocalDateTime getPickupTime() {
        return pickupTime;
    }
    public void setPickupTime(LocalDateTime pickupTime) {
        this.pickupTime = pickupTime;
    }

}

