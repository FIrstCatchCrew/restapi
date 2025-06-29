package com.firstcatchcrew.restapi.orderItem;

import java.math.BigDecimal;

public class OrderItemViewDTO {
    private Long id;
    private Long catchId;
    private String speciesName;
    private BigDecimal quantity;
    private BigDecimal price;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCatchId() { return catchId;}
    public void setCatchId(Long catchId) { this.catchId = catchId; }

    public String getSpeciesName() { return speciesName; }
    public void setSpeciesName(String speciesName) { this.speciesName = speciesName; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public BigDecimal getQuantity() { return quantity; }
    public void setQuantity(BigDecimal quantity) { this.quantity = quantity; }


}
