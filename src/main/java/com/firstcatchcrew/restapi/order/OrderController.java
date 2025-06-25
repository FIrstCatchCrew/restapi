package com.firstcatchcrew.restapi.order;

import com.firstcatchcrew.restapi.fishCatch.dto.CatchViewDTO;
import com.firstcatchcrew.restapi.order.dto.OrderCreateDTO;
import com.firstcatchcrew.restapi.order.dto.OrderViewDTO;
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
    public ResponseEntity<List<OrderViewDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderViewDTO> getOrderById(@PathVariable long id) {
        OrderViewDTO dto = orderService.getOrderById(id);
        return (dto != null) ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping("/customer/{username}")
    public ResponseEntity<List<OrderViewDTO>> getOrdersByCustomer(@PathVariable String username) {
        List<OrderViewDTO> dtos = orderService.getOrdersByCustomerUsername(username);
        return ResponseEntity.ok(dtos); // Return 200 OK even if empty
    }

    @PostMapping
    public ResponseEntity<OrderViewDTO> createOrder(@RequestBody OrderCreateDTO dto) {
        OrderViewDTO savedDto = orderService.createOrder(dto);
        return ResponseEntity
                .created(URI.create("/api/order/" + savedDto.getOrderId()))
                .body(savedDto);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<OrderViewDTO> updateOrder(
//            @PathVariable Long id,
//            @RequestBody OrderCreateDTO dto
//    ) {
//        return orderService.updateOrder(id, dto)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return orderService.deleteByOrderId(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}


