package com.firstcatchcrew.restapi.orderItem;

import java.math.BigDecimal;

public class OrderItemViewDTO {
    private Long catchId;
    private String speciesName;
    private BigDecimal quantity;
    private BigDecimal price;

    // getters & setters

    public Long getCatchId() { return catchId;}

    public String getSpeciesName() { return speciesName; }
    public void setSpeciesName(String speciesName) { this.speciesName = speciesName; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public BigDecimal getQuantity() { return quantity; }
    public void setQuantity(BigDecimal quantity) { this.quantity = quantity; }


}
