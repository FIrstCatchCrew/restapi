package com.example.firstcatch_api.order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

import java.time.LocalDate;

@Entity
public class Order {
    @Id
    @SequenceGenerator(name = "order_sequence", sequenceName = "order_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "order_sequence")
    private Long id;
    private LocalDate date;
    private String status;
    private Long customerID; //update
}
