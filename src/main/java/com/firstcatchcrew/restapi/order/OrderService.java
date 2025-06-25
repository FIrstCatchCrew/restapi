package com.firstcatchcrew.restapi.order;

import com.firstcatchcrew.restapi.fishCatch.CatchRepository;
import com.firstcatchcrew.restapi.order.dto.OrderCreateDTO;
import com.firstcatchcrew.restapi.order.dto.OrderViewDTO;
import com.firstcatchcrew.restapi.orderItem.OrderItem;
import com.firstcatchcrew.restapi.orderItem.OrderItemMapper;
import com.firstcatchcrew.restapi.orderItem.OrderItemRepository;
import com.firstcatchcrew.restapi.person.Person;
import com.firstcatchcrew.restapi.person.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<OrderViewDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(OrderMapper::from)
                .toList();
    }

    public OrderViewDTO getOrderById(long id) {
        return orderRepository.findById(id)
                .map(OrderMapper::from)
                .orElse(null);
    }

    public List<OrderViewDTO> getOrdersByCustomerUsername(String username) {
        return orderRepository.findAllByCustomerUsername(username)
                .stream()
                .map(OrderMapper::from)
                .toList();
    }

    @Transactional
    public OrderViewDTO createOrder(OrderCreateDTO dto) {
        Person customer = personRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        List<OrderItem> items = dto.getOrderItems().stream()
                .map(itemDto -> {
                    var fishCatch = catchRepository.findById(itemDto.getCatchId())
                            .orElseThrow(() -> new IllegalArgumentException("Catch not found"));
                    return OrderItemMapper.toEntity(itemDto, fishCatch);
                })
                .toList();

        Order order = OrderMapper.toEntity(dto, customer, items);
        return OrderMapper.from(orderRepository.save(order));
    }


    @Transactional
    public OrderViewDTO updateOrder(Long id, OrderCreateDTO dto) {
        Optional<Order> optionalOrder = orderRepository.findById(id);

        if (optionalOrder.isEmpty()) return null;

        Order existingOrder = optionalOrder.get();

        existingOrder.setOrderStatus(dto.getOrderStatus());

        List<OrderItem> oldItems = existingOrder.getOrderItems();
        orderItemRepository.deleteAllInBatch(oldItems);
        oldItems.clear();

        List<OrderItem> newItems = dto.getOrderItems().stream()
                .map(itemDto -> {
                    var fishCatch = catchRepository.findById(itemDto.getCatchId())
                            .orElseThrow(() -> new IllegalArgumentException("Catch not found: " + itemDto.getCatchId()));
                    return OrderItemMapper.toEntity(itemDto, fishCatch);
                })
                .toList();

        newItems.forEach(item -> item.setOrder(existingOrder));
        existingOrder.setOrderItems(newItems);

        Order saved = orderRepository.save(existingOrder);
        return OrderMapper.from(saved);
    }

    public boolean deleteByOrderId(Long id) {
        if (!orderRepository.existsById(id)) return false;
        orderRepository.deleteById(id);
        return true;
    }
}
