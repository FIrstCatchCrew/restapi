package com.firstcatchcrew.restapi.order;

import com.firstcatchcrew.restapi.order.dto.OrderCreateDTO;
import com.firstcatchcrew.restapi.order.dto.OrderViewDTO;
import com.firstcatchcrew.restapi.orderItem.OrderItem;
import com.firstcatchcrew.restapi.orderItem.OrderItemMapper;
import com.firstcatchcrew.restapi.orderItem.OrderItemViewDTO;
import com.firstcatchcrew.restapi.person.Person;

import java.time.LocalDateTime;
import java.util.List;

public class OrderMapper {

    public static Order toEntity(OrderCreateDTO dto,
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

    public static OrderViewDTO from(Order order) {
        OrderViewDTO dto = new OrderViewDTO();

        dto.setId(order.getId());
        dto.setOrderDateTime(order.getOrderDateTime());
        dto.setCustomerUsername(order.getCustomer().getUsername());
        dto.setOrderStatus(order.getOrderStatus().toString());

        List<OrderItemViewDTO> itemDTOs = order.getOrderItems().stream()
                .map(OrderItemMapper::from)
                .toList();

        dto.setOrderItems(itemDTOs);

        return dto;
    }
}
