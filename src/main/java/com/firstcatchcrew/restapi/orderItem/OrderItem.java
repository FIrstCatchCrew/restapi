package com.firstcatchcrew.restapi.orderItem;

import com.firstcatchcrew.restapi.fishCatch.Catch;
import com.firstcatchcrew.restapi.order.Order;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class OrderItem {
    @Id
    @SequenceGenerator(name = "orderItem_sequence", sequenceName = "orderItem_sequence", allocationSize = 1)
    @GeneratedValue(generator = "orderItem_sequence")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catch_id")
    private Catch fishCatch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(nullable = false)
    private BigDecimal quantity;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Catch getFishCatch() {
        return fishCatch;
    }
    public void setFishCatch(Catch fishCatch) {
        this.fishCatch = fishCatch;
    }

    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() { return fishCatch.getPrice(); }
    public void setPrice(BigDecimal price) { fishCatch.setPrice(price); }

}
