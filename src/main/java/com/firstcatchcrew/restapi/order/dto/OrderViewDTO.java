package com.firstcatchcrew.restapi.order.dto;

import com.firstcatchcrew.restapi.orderItem.OrderItemViewDTO;

import java.time.LocalDateTime;
import java.util.List;

public class OrderViewDTO {

    private Long id;
    private String customerUsername;
    private LocalDateTime orderDateTime;
    private String orderStatus;
    private List<OrderItemViewDTO> orderItems;
    
    public Long getId() {
        return id;
    }
    public void setId(Long orderId) {
        this.id = orderId;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }
    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }
    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderItemViewDTO> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(List<OrderItemViewDTO> orderItems) {
        this.orderItems = orderItems;
    }
}
