package com.firstcatchcrew.restapi.order;


import com.firstcatchcrew.restapi.fishCatch.CatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; // Introduced to return status codes
import org.springframework.web.bind.annotation.*;
import java.net.URI;

import java.util.List;

@RestController
@CrossOrigin
public class OrderController {

    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //Status response: 200 OK
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> all = orderService.getAllOrders();
        return ResponseEntity.ok(all);
    }

    // Orders by customer, to be connected to CLI, answer question. Status responses: 200 OK, 404 Not Found
    @GetMapping("/order/{customerId}")
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable long customerId) {
        List<Order> orders = orderService.getOrdersByCustomerId(customerId);
        if (orders.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orders);
    }

    //Status responses: 201 Created + location(orderId) header
    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody Order newOrder) {
        Order saved = orderService.create(newOrder);
        URI location = URI.create("/api/orders/" + saved.getOrderId());
        return ResponseEntity.created(location).body(saved);
    }

    //Status responses: 200 OK, 404 Not Found
    @PutMapping("/order/{id}")
    public ResponseEntity<Order> updateOrder(
            @PathVariable Long id,
            @RequestBody Order updatedOrder
    ) {
        return orderService.update(id, updatedOrder)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Status responses: 204 No content, 404 Not Found
    @DeleteMapping("/order/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        boolean deleted = orderService.delete(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}






