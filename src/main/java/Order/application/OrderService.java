package Order.application;

import Order.application.exceptions.OrderException;
import Order.domain.dtos.OrderDto;
import Order.domain.mapper.OrderMapper;
import Order.infrastructure.persistance.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
    }

    public OrderDto createOrder(@Valid OrderDto orderDto) {
        try {
            var order = orderMapper.toEntity(orderDto);
            return orderMapper.toDto(orderRepository.save(order));
        } catch(RuntimeException exception) {
            throw new OrderException(exception.getMessage());
        }
    }
}
