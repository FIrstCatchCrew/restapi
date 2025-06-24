package com.firstcatchcrew.restapi.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@CrossOrigin
public class OrderController {

    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> all() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Order>> byCustomer(@PathVariable long customerId) {
        var list = orderService.getOrdersByCustomerId(customerId);
        return list.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderCreateDTO dto) {
        Order saved = orderService.create(dto);
        return ResponseEntity
                .created(URI.create("/api/orders/" + saved.getOrderId()))
                .body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> update(
            @PathVariable Long id,
            @RequestBody OrderCreateDTO dto
    ) {
        return orderService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return orderService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}


