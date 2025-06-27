package com.firstcatchcrew.restapi.fishCatch.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;


@Embeddable
public class PickupInfo {

    @Column(name = "pickup_instructions")
    private String instructions;

    @Column(name = "pickup_time")
    private LocalDateTime pickupTime;

    public PickupInfo() {
    }

    public PickupInfo(String instructions, LocalDateTime pickupTime) {
        this.instructions = instructions;
        this.pickupTime = pickupTime;
    }

    public String getInstructions() {
        return instructions;
    }
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    public LocalDateTime getPickupTime() {
        return pickupTime;
    }
    public void setPickupTime(LocalDateTime pickupTime) {
        this.pickupTime = pickupTime;
    }

}