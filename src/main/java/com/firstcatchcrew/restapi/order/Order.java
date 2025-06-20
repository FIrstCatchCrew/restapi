package com.firstcatchcrew.restapi.order;

import com.firstcatchcrew.restapi.orderItem.OrderItem;
import com.firstcatchcrew.restapi.person.Person;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Order {
    @Id
    @SequenceGenerator(name = "order_sequence", sequenceName = "order_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "order_sequence")
    private Long orderId;
    private LocalDateTime orderDateTime;
    private Boolean orderStatus;//Changed from String

    @ManyToOne
    private Person customer;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public Boolean getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Boolean status) {
        this.orderStatus = orderStatus;
    }

    public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer = customer;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String toString() {
        return "Order:{" +
                "orderId=" + orderId +
                ", orderDateTime=" + orderDateTime +
                ", orderStatus=" + orderStatus +
                ", customer=" + customer +
                ", orderItems=" + orderItems +
                '}' ;
    }
}
