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
        return orderRepository.findByCustomerUsername(username)
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


//    @Transactional
//    public OrderViewDTO updateOrder(Long id, OrderCreateDTO dto) {
//        return orderRepository.findById(id).map(existing -> {
//            // update status
//            existing.setOrderStatus(dto.getOrderStatus());
//            // update items: delete old, add new
//            existing.getOrderItems().clear();
//            orderItemRepository.deleteAllInBatch(existing.getOrderItems());
//            List<OrderItem> items = dto.getOrderItems().stream()
//                    .map(item -> {
//                        var fishCatch = catchRepository.findById(item.getCatchId())
//                                .orElseThrow(() -> new IllegalArgumentException("Catch not found"));
//                        return OrderMapper.from(item, fishCatch);
//                    })
//                    .collect(Collectors.toList());
//            existing.setOrderItems(items);
//            items.forEach(item -> item.setOrder(existing));
//            return orderRepository.save(existing);
//        });
//    }

    public boolean deleteByOrderId(Long id) {
        if (!orderRepository.existsById(id)) return false;
        orderRepository.deleteById(id);
        return true;
    }
}
