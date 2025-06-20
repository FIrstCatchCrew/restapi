package com.firstcatchcrew.restapi.order;


import com.firstcatchcrew.restapi.person.Person;
import com.firstcatchcrew.restapi.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PersonRepository personRepository;

    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    public List<Order> getOrdersByCustomerId(long customerId) {
        Person customer = personRepository.findById(customerId).orElse(null);
        if (customer == null) return List.of();
        return orderRepository.findAllByCustomer(customer);
    }
}
