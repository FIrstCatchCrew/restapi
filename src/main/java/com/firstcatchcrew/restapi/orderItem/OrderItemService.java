package com.firstcatchcrew.restapi.orderItem;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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

    @Transactional
    public OrderItem create(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @Transactional
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

