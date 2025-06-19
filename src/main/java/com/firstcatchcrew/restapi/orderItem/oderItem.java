package com.firstcatchcrew.restapi.orderItem;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class oderItem {
    @Id
    @SequenceGenerator(name = "orderItem_sequence", sequenceName = "orderItem_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "orderItem_sequence")
    private Long id;
    private Long orderID;
    private Long catchID;
    private Long quantity;

}
