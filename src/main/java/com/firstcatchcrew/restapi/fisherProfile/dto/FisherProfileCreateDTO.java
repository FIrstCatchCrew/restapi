package com.firstcatchcrew.restapi.fisherProfile.dto;

public class FisherProfileCreateDTO {
    private Long personId;
    private String fishingLicenseNumber;
    private Long defaultLandingId;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getFishingLicenseNumber() {
        return fishingLicenseNumber;
    }

    public void setFishingLicenseNumber(String fishingLicenseNumber) {
        this.fishingLicenseNumber = fishingLicenseNumber;
    }

    public Long getDefaultLandingId() {
        return defaultLandingId;
    }

    public void setDefaultLandingId(Long defaultLandingId) {
        this.defaultLandingId = defaultLandingId;
    }
}
