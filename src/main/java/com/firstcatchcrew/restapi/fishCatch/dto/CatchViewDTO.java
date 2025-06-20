package com.firstcatchcrew.restapi.fishCatch.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// Used when returning a Catch to the client (GET request).
// This one is for client display — includes id, availability info, and human-readable names if you want them.

public class CatchViewDTO {

    private Long id;
    private String fisherName;
    private String speciesName;

    private LocalDateTime catchDate;
    private BigDecimal quantityInKg;
    private BigDecimal price;
    private boolean available;
    private boolean sold;

    private String pickupLocationName;
    private String pickupAddress;
    private LocalDateTime pickupTime;

    private double latitude;
    private double longitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFisherName() {
        return fisherName;
    }

    public void setFisherName(String fisherName) {
        this.fisherName = fisherName;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public String getPickupLocationName() {
        return pickupLocationName;
    }

    public void setPickupLocationName(String pickupLocationName) {
        this.pickupLocationName = pickupLocationName;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
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

