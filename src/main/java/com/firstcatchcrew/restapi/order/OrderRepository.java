package com.firstcatchcrew.restapi.order;

import com.firstcatchcrew.restapi.person.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findAllByCustomer(Person customer);
    List<Order> findAllByOrderStatus(OrderStatus orderStatus);
    List<Order> findAll();
    List<Order> findAllByCustomerUsername(String username);
    List<Order> findAllByOrderStatusAndCustomer(OrderStatus orderStatus, Person customer);

}
