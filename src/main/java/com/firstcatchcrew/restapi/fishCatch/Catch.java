package com.firstcatchcrew.restapi.fishCatch;

import com.firstcatchcrew.restapi.fishCatch.embedded.GeoLocation;
import com.firstcatchcrew.restapi.fishCatch.embedded.PickupInfo;
import com.firstcatchcrew.restapi.fisherProfile.FisherProfile;
import com.firstcatchcrew.restapi.orderItem.OrderItem;
import com.firstcatchcrew.restapi.species.Species;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Catch {

    @Id
    @SequenceGenerator(name = "catch_sequence", sequenceName = "catch_sequence", allocationSize = 1)
    @GeneratedValue(generator = "catch_sequence")

    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fisher_id")
    private FisherProfile fisher;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "species_id")
    private Species species;

    @OneToMany(mappedBy = "fishCatch", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(nullable = false)
    private boolean available;

    private LocalDateTime catchDate;
    private BigDecimal quantityInKg;
    private BigDecimal price;

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
        this.catchDate = LocalDateTime.now();
        this.geoLocation = geoLocation;
        this.pickupInfo = new PickupInfo("TBD", "TBD", this.catchDate.withHour(12).withMinute(0));
        this.updateAvailabilityStatus();
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

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        this.updateAvailabilityStatus();
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean shouldBeAvailable() {
        boolean notSold = this.orderItems == null;
        boolean pickupStillValid = pickupInfo != null && pickupInfo.getPickupTime().isAfter(LocalDateTime.now());
        return notSold && pickupStillValid;
    }

    public void updateAvailabilityStatus() {
        boolean notSold = this.orderItems == null || this.orderItems.isEmpty();
        boolean pickupStillValid = pickupInfo != null && pickupInfo.getPickupTime().isAfter(LocalDateTime.now());
        this.available = notSold && pickupStillValid;
    }


    public PickupInfo getPickupInfo() { return pickupInfo; }
    public void setPickupInfo(PickupInfo pickupInfo) {
        this.pickupInfo = pickupInfo;
        updateAvailabilityStatus();
    }

    public GeoLocation getGeoLocation() { return geoLocation; }
    public void setGeoLocation(GeoLocation geoLocation) { this.geoLocation = geoLocation; }
}
