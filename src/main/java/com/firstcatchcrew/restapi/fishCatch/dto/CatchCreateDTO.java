package com.firstcatchcrew.restapi.fishCatch.dto;

// Used when creating a new Catch (usually from a POST request).
// No id, available, or sold — those are managed by the server or have defaults.

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CatchCreateDTO {

    private Long fisherId;
    private Long speciesId;

    private LocalDateTime catchDate;
    private BigDecimal quantityInKg;
    private BigDecimal price;

    private String pickupLocationName;
    private String pickupAddress;
    private LocalDateTime pickupTime;

    private double latitude;
    private double longitude;

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
