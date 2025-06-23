package com.firstcatchcrew.restapi.order;


import com.firstcatchcrew.restapi.person.Person;
import com.firstcatchcrew.restapi.person.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PersonRepository personRepository;

    public OrderService(
            OrderRepository orderRepository,
            PersonRepository personRepository
    ) {
        this.orderRepository = orderRepository;
        this.personRepository = personRepository;
    }


    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    public List<Order> getOrdersByCustomerId(long customerId) {
        Person customer = personRepository.findById(customerId).orElse(null);
        if (customer == null) return List.of();
        return orderRepository.findAllByCustomer(customer);
    }

    public Order create(Order order) {
        order.setOrderDateTime(LocalDateTime.now());
        order.setOrderStatus(false);
        return orderRepository.save(order);
    }

    public Optional<Order> update(Long id, Order updated) {
        return orderRepository.findById(id)
                .map(existing -> {
                    existing.setOrderDateTime(updated.getOrderDateTime());
                    existing.setOrderStatus(updated.getOrderStatus());
                    existing.setCustomer(updated.getCustomer());
                    existing.setOrderItems(updated.getOrderItems());
                    return orderRepository.save(existing);
                });
    }

    public boolean delete(Long id) {
        if (!orderRepository.existsById(id)) return false;
        orderRepository.deleteById(id);
        return true;
    }
}
