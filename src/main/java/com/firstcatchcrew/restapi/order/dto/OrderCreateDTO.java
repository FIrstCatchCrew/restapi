package com.firstcatchcrew.restapi.order.dto;

import com.firstcatchcrew.restapi.order.OrderStatus;
import com.firstcatchcrew.restapi.orderItem.OrderItemViewDTO;

import java.util.List;

public class OrderCreateDTO {
    private Long customerId;
    private OrderStatus orderStatus;
    private List<OrderItemViewDTO> orderItems;

    // getters & setters
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public OrderStatus getOrderStatus() { return orderStatus; }
    public void setOrderStatus(OrderStatus orderStatus) { this.orderStatus = orderStatus; }

    public List<OrderItemViewDTO> getOrderItems() { return orderItems; }
    public void setOrderItems(List<OrderItemViewDTO> orderItems) { this.orderItems = orderItems; }
}
