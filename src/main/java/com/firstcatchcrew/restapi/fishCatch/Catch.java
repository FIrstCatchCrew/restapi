package com.firstcatchcrew.restapi.fishCatch;

import com.firstcatchcrew.restapi.fishCatch.embedded.GeoLocation;
import com.firstcatchcrew.restapi.fishCatch.embedded.PickupInfo;
import com.firstcatchcrew.restapi.fisherProfile.FisherProfile;
import com.firstcatchcrew.restapi.landing.Landing;
import com.firstcatchcrew.restapi.species.Species;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Catch {

    @Id
    @SequenceGenerator(name = "catch_sequence", sequenceName = "catch_sequence", allocationSize = 1)
    @GeneratedValue(generator = "catch_sequence")

    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fisher_profile_id")
    private FisherProfile fisher;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "species_id")
    private Species species;

    @Column(nullable = false)
    private boolean available;

    private boolean sold;

    private LocalDateTime timeStamp;
    private BigDecimal quantityInKg;
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "landing_id")
    private Landing landing;

    @Embedded
    private PickupInfo pickupInfo;
    @Embedded
    private GeoLocation geoLocation;

    public Catch() { }

    public Catch(Species species, FisherProfile fisher, BigDecimal quantityInKg, BigDecimal price, GeoLocation geoLocation) {
        this.species = species;
        this.fisher = fisher;
        this.quantityInKg = quantityInKg;
        this.price = price;
        this.timeStamp = LocalDateTime.now();
        this.geoLocation = geoLocation;
        this.pickupInfo = new PickupInfo("TBD", this.timeStamp.withHour(12).withMinute(0));
        this.refreshAvailability();
    }

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

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
        refreshAvailability();
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

    public Landing getLanding() {
        return landing;
    }
    public void setLanding(Landing landing) {
        this.landing = landing;
    }

    public boolean isAvailable() {
        return available;
    }
    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
        refreshAvailability();
    }
    public void refreshAvailability() {
        this.available = !sold && isPickupStillValid();
    }

    private boolean isPickupStillValid() {
        if (pickupInfo == null || pickupInfo.getPickupTime() == null) return false;

        return pickupInfo.getPickupTime().isAfter(LocalDateTime.now());
    }

    public PickupInfo getPickupInfo() { return pickupInfo; }
    public void setPickupInfo(PickupInfo pickupInfo) {
        this.pickupInfo = pickupInfo;
        refreshAvailability();
    }

    public GeoLocation getGeoLocation() { return geoLocation; }
    public void setGeoLocation(GeoLocation geoLocation) { this.geoLocation = geoLocation; }
}