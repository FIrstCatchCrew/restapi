package com.example.firstcatch_api.fishCatch;

import com.example.firstcatch_api.fishCatch.embedded.GeoLocation;
import com.example.firstcatch_api.fishCatch.embedded.PickupInfo;
import jakarta.persistence.*;

@Entity
public class Catch {

    @Id
//    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1, initialValue=1)
//    @GeneratedValue(generator = "product_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Long fisherID; // this should be fixed
    private Long speciesID; //this should be fixed
    private Long catchDate;
    private Long quantityInKg;
    private Long price;

    @Embedded
    private PickupInfo pickupInfo;
    @Embedded
    private GeoLocation geoLocation;

}
