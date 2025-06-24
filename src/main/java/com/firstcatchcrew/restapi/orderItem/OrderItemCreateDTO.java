package com.firstcatchcrew.restapi.orderItem;

public class OrderItemCreateDTO {
    private Long catchId;
    private Long quantity;

    // getters & setters
    public Long getCatchId() { return catchId; }
    public void setCatchId(Long catchId) { this.catchId = catchId; }

    public Long getQuantity() { return quantity; }
    public void setQuantity(Long quantity) { this.quantity = quantity; }
}
