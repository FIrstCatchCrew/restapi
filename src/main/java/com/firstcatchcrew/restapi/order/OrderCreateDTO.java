package com.firstcatchcrew.restapi.order;

import com.firstcatchcrew.restapi.orderItem.OrderItemCreateDTO;

import java.util.List;

public class OrderCreateDTO {
    private Long customerId;
    private Boolean orderStatus;
    private List<OrderItemCreateDTO> orderItems;

    // getters & setters
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public Boolean getOrderStatus() { return orderStatus; }
    public void setOrderStatus(Boolean orderStatus) { this.orderStatus = orderStatus; }

    public List<OrderItemCreateDTO> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItemCreateDTO> orderItems) { this.orderItems = orderItems; }
}
