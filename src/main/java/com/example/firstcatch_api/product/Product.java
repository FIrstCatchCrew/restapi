package com.example.firstcatch_api.product;

import com.example.firstcatch_api.product.embedded.GeoLocation;
import com.example.firstcatch_api.product.embedded.PickupInfo;
import jakarta.persistence.*;

@Entity
public class Product {

    @Id
//    @SequenceGenerator(name = "product_sequence", sequenceName = "product_sequence", allocationSize = 1, initialValue=1)
//    @GeneratedValue(generator = "product_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Embedded
    private PickupInfo pickupInfo;
    @Embedded
    private GeoLocation geoLocation;



}
