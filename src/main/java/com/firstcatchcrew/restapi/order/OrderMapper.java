package com.firstcatchcrew.restapi.order;

import com.firstcatchcrew.restapi.fishCatch.Catch;
import com.firstcatchcrew.restapi.orderItem.OrderItem;
import com.firstcatchcrew.restapi.orderItem.OrderItemCreateDTO;
import com.firstcatchcrew.restapi.person.Person;

import java.time.LocalDateTime;
import java.util.List;

public class OrderMapper {

    public static Order fromCreateDTO(OrderCreateDTO dto,
                                      Person customer,
                                      List<OrderItem> items) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDateTime(LocalDateTime.now());
        order.setOrderStatus(dto.getOrderStatus());
        order.setOrderItems(items);
        items.forEach(i -> i.setOrder(order));
        return order;
    }

    public static OrderItem fromCreateDTO(OrderItemCreateDTO dto, Catch fishCatch) {
        OrderItem item = new OrderItem();
        item.setFishCatch(fishCatch);
        item.setQuantity(dto.getQuantity());
        // Do NOT modify fishCatch.getOrderItems() here. That belongs in service logic.
        return item;
    }
}
