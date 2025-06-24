package com.firstcatchcrew.restapi.orderItem;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public Optional<OrderItem> findById(Long id) {
        return orderItemRepository.findById(id);
    }

    public OrderItem create(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public Optional<OrderItem> update(Long id, OrderItem updatedOrderItem) {
        return orderItemRepository.findById(id)
                .map(existing -> {
                    existing.setFishCatch(updatedOrderItem.getFishCatch());
                    existing.setQuantity(updatedOrderItem.getQuantity());
                    existing.setOrder(updatedOrderItem.getOrder());
                    return orderItemRepository.save(existing);
                });
    }

    public boolean delete(Long id) {
        if (!orderItemRepository.existsById(id)) return false;
        orderItemRepository.deleteById(id);
        return true;
    }
}

