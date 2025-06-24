package com.firstcatchcrew.restapi.order;

import com.firstcatchcrew.restapi.fishCatch.CatchRepository;
import com.firstcatchcrew.restapi.orderItem.OrderItem;
import com.firstcatchcrew.restapi.orderItem.OrderItemCreateDTO;
import com.firstcatchcrew.restapi.orderItem.OrderItemRepository;
import com.firstcatchcrew.restapi.person.Person;
import com.firstcatchcrew.restapi.person.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PersonRepository personRepository;
    private final CatchRepository catchRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository,
                        PersonRepository personRepository,
                        CatchRepository catchRepository,
                        OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.personRepository = personRepository;
        this.catchRepository = catchRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByCustomerId(long customerId) {
        Person customer = personRepository.findById(customerId).orElse(null);
        return (customer == null)
                ? List.of()
                : orderRepository.findAllByCustomer(customer);
    }

    @Transactional
    public Order create(OrderCreateDTO dto) {
        Person customer = personRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        // map items
        List<OrderItem> items = dto.getOrderItems().stream()
                .map(iDto -> {
                    var fishCatch = catchRepository.findById(iDto.getCatchId())
                            .orElseThrow(() -> new IllegalArgumentException("Catch not found"));
                    return OrderMapper.fromCreateDTO(iDto, fishCatch);
                })
                .collect(Collectors.toList());
        var order = OrderMapper.fromCreateDTO(dto, customer, items);
        return orderRepository.save(order);
    }

    @Transactional
    public Optional<Order> update(Long id, OrderCreateDTO dto) {
        return orderRepository.findById(id).map(existing -> {
            // update status
            existing.setOrderStatus(dto.getOrderStatus());
            // update items: delete old, add new
            existing.getOrderItems().clear();
            orderItemRepository.deleteAllInBatch(existing.getOrderItems());
            List<OrderItem> items = dto.getOrderItems().stream()
                    .map(iDto -> {
                        var fishCatch = catchRepository.findById(iDto.getCatchId())
                                .orElseThrow(() -> new IllegalArgumentException("Catch not found"));
                        return OrderMapper.fromCreateDTO(iDto, fishCatch);
                    })
                    .collect(Collectors.toList());
            existing.setOrderItems(items);
            items.forEach(i -> i.setOrder(existing));
            return orderRepository.save(existing);
        });
    }

    public boolean delete(Long id) {
        if (!orderRepository.existsById(id)) return false;
        orderRepository.deleteById(id);
        return true;
    }
}
