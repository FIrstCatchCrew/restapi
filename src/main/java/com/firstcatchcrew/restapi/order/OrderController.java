package com.firstcatchcrew.restapi.order;

import com.firstcatchcrew.restapi.order.dto.OrderCreateDTO;
import com.firstcatchcrew.restapi.order.dto.OrderViewDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    public ResponseEntity<List<OrderViewDTO>> getAll() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderViewDTO> getById(@PathVariable long id) {
        OrderViewDTO dto = orderService.getOrderById(id);
        return (dto != null) ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping("/customer/{username}")
    public ResponseEntity<List<OrderViewDTO>> getOrdersByCustomer(@PathVariable String username) {
        List<OrderViewDTO> dtos = orderService.getOrdersByCustomerUsername(username);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<OrderViewDTO> create(@Validated @RequestBody OrderCreateDTO dto) {
        OrderViewDTO saved = orderService.createOrder(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderViewDTO> updateOrder(
            @PathVariable Long id,
            @RequestBody OrderCreateDTO dto) {
        OrderViewDTO updatedOrder = orderService.updateOrder(id, dto);
        return (updatedOrder != null) ? ResponseEntity.ok(updatedOrder) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return orderService.deleteByOrderId(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}


