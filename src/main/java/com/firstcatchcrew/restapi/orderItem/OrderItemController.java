package com.firstcatchcrew.restapi.orderItem;


import com.firstcatchcrew.restapi.order.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin
public class OrderItemController {
    private final OrderItemService orderItemService;
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/orderItem")
    public ResponseEntity<List<OrderItem>> getAll() {
        return ResponseEntity.ok(orderItemService.getAllOrderItems());
    }

    @GetMapping("/orderItem/{id}")
    public ResponseEntity<OrderItem> getByID(@PathVariable("id") Long orderItemId) {
        return orderItemService.findById(orderItemId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/orderItem")
    public ResponseEntity<OrderItem> create(@RequestBody OrderItem newOrderItem) {
        OrderItem saved = orderItemService.create(newOrderItem);
        URI location = URI.create("/api/orderItem/" + saved.getOrderItemId());
        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/orderItem/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(
            @PathVariable("id") Long orderItemId,
            @RequestBody OrderItem updatedOrderItem
    ) {
        return orderItemService.update(orderItemId, updatedOrderItem)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/orderItem/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable("id") Long orderItemId) {
        boolean deleted = orderItemService.delete(orderItemId);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
