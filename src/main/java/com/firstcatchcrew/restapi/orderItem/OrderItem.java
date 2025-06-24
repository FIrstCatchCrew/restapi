package com.firstcatchcrew.restapi.orderItem;

import com.firstcatchcrew.restapi.fishCatch.Catch;
import com.firstcatchcrew.restapi.order.Order;
import jakarta.persistence.*;

@Entity
public class OrderItem {
    @Id
    @SequenceGenerator(name = "orderItem_sequence", sequenceName = "orderItem_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "orderItem_sequence")
    private Long orderItemId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catch_id")
    private Catch fishCatch;

    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;


    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Catch getFishCatch() {
        return fishCatch;
    }

    public void setFishCatch(Catch fishCatch) {
        this.fishCatch = fishCatch;
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
                ", Catch=" + fishCatch +
                ", quantity=" + quantity +
                ", order=" + order +
                '}' ;
    }
}
