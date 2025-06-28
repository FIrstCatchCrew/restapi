package com.firstcatchcrew.restapi.fishCatch.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CatchCreateDTO {
    private Long id;

    private Long fisherId;
    private Long speciesId;

    private LocalDateTime catchDate;
    private BigDecimal quantityInKg;
    private BigDecimal price;

    private Long landingId;
    private String pickupInstructions;
    private LocalDateTime pickupTime;

    private double latitude;
    private double longitude;

    public Long getId(){return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFisherId() {
        return fisherId;
    }

    public void setFisherId(Long fisherId) {
        this.fisherId = fisherId;
    }

    public Long getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(Long speciesId) {
        this.speciesId = speciesId;
    }

    public LocalDateTime getCatchDate() {
        return catchDate;
    }

    public void setCatchDate(LocalDateTime catchDate) {
        this.catchDate = catchDate;
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

    public Long getLandingId() {return landingId;}
    public void setLandingId(Long landingId) {this.landingId = landingId;}

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
