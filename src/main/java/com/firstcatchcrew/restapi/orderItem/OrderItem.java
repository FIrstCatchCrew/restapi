package com.firstcatchcrew.restapi.orderItem;

import com.firstcatchcrew.restapi.order.Order;
import jakarta.persistence.*;

@Entity
public class OrderItem {
    @Id
    @SequenceGenerator(name = "orderItem_sequence", sequenceName = "orderItem_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "orderItem_sequence")
    private Long orderItemId;
    private Long catchID;
    private Long quantity;

    @ManyToOne
    private Order order;


    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getCatchID() {
        return catchID;
    }

    public void setCatchID(Long catchID) {
        this.catchID = catchID;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", catchID=" + catchID +
                ", quantity=" + quantity +
                ", order=" + order +
                '}' ;
    }
}
