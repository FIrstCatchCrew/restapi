package com.firstcatchcrew.restapi.orderItem;

import com.firstcatchcrew.restapi.fishCatch.Catch;

public class OrderItemMapper {

    public static OrderItem toEntity(OrderItemViewDTO dto, Catch fishCatch) {
        OrderItem orderItem = new OrderItem();
        orderItem.setFishCatch(fishCatch);
        orderItem.setQuantity(dto.getQuantity());
        return orderItem;
    }

    public static OrderItemViewDTO from(OrderItem orderItem) {
        OrderItemViewDTO dto = new OrderItemViewDTO();
        dto.setId(orderItem.getId());
        dto.setCatchId(orderItem.getFishCatch().getId());
        dto.setSpeciesName(orderItem.getFishCatch().getSpecies().getName());
        dto.setPrice(orderItem.getFishCatch().getPrice());
        dto.setQuantity(orderItem.getQuantity());
        return dto;
    }
}

