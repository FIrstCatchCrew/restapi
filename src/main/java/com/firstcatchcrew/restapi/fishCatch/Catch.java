package com.firstcatchcrew.restapi.fishCatch;

import com.firstcatchcrew.restapi.fishCatch.embedded.GeoLocation;
import com.firstcatchcrew.restapi.fishCatch.embedded.PickupInfo;
import com.firstcatchcrew.restapi.fisherProfile.FisherProfile;
import com.firstcatchcrew.restapi.species.Species;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Catch {

    @Id
    @SequenceGenerator(name = "catch_sequence", sequenceName = "catch_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "catch_sequence")

    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fisherProfile_id")
    private FisherProfile fisher;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "species_id")
    private Species species;

    private LocalDateTime catchDate;
    private BigDecimal quantityInKg;
    private BigDecimal price;
    private boolean available;
    private boolean sold;

    @Embedded
    private PickupInfo pickupInfo;
    @Embedded
    private GeoLocation geoLocation;

    public Long getId() {
        return id;
    }

    public FisherProfile getFisher() {
        return fisher;
    }

    public void setFisher(FisherProfile fisher) {
        this.fisher = fisher;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
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

    private void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isSold() {
        return sold;
    }

    private void setSold(boolean sold) {
        this.sold = sold;
    }

    public void markAsSold() {
        this.sold = true;
        this.available = false;
    }

    // CLEANUP: Let you mark it available again manually, but reset sold flag too
    public void markAsAvailable() {
        this.available = true;
        this.sold = false;
    }

    public PickupInfo getPickupInfo() { return pickupInfo; }
    public void setPickupInfo(PickupInfo pickupInfo) { this.pickupInfo = pickupInfo; }

    public GeoLocation getGeoLocation() { return geoLocation; }
    public void setGeoLocation(GeoLocation geoLocation) { this.geoLocation = geoLocation; }
}
