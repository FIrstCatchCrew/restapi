package com.firstcatchcrew.restapi.fisherProfile;

public class FisherProfileDTO {
    private String fishingLicenseNumber;
    private Long personId;
    private Long defaultLandingId;

    // Getters and setters
    public String getFishingLicenseNumber() {
        return fishingLicenseNumber;
    }

    public void setFishingLicenseNumber(String fishingLicenseNumber) {
        this.fishingLicenseNumber = fishingLicenseNumber;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getDefaultLandingId() {
        return defaultLandingId;
    }

    public void setDefaultLandingId(Long defaultLandingId) {
        this.defaultLandingId = defaultLandingId;
    }
}