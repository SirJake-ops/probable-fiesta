package Order.application;

import Order.application.exceptions.OrderException;
import Order.domain.dtos.OrderDto;
import Order.domain.mapper.OrderMapper;
import Order.domain.models.Order;
import Order.enums.OrderStatus;
import Order.enums.OrderType;
import Order.enums.Side;
import Order.events.OrderPlacedEvent;
import Order.infrastructure.persistence.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public OrderDto createOrder(@Valid OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        orderRepository.save(order);
        applicationEventPublisher.publishEvent(new OrderPlacedEvent(order));
        return orderMapper.toDto(order);
    }

    public OrderDto updateOrder(@Valid OrderDto orderDto, UUID id) {
        Optional<Order> order = orderRepository.findOrderById(id);
        if (order.isPresent()) {
            order.get().setPrice(orderDto.getPrice() != null ? orderDto.getPrice() : BigDecimal.ZERO);
            order.get().setQuantity(orderDto.getQuantity() != null ? orderDto.getQuantity() : BigDecimal.ZERO);
            order.get().setSymbol(orderDto.getSymbol() != null ? orderDto.getSymbol() : "");
            order.get().setOrderType(orderDto.getOrderType() != null ? orderDto.getOrderType() : OrderType.MARKET);
            order.get().setSide(orderDto.getSide() != null ? orderDto.getSide() : Side.BUY);
            order.get().setFilledQuantity(orderDto.getFilledQuantity() != null ? orderDto.getFilledQuantity() : BigDecimal.ZERO);
            order.get().setStatus(orderDto.getStatus() != null ? orderDto.getStatus() : OrderStatus.PENDING);
            return orderMapper.toDto(orderRepository.save(order.get()));
        } else {
            throw new OrderException("Order not found");
        }
    }

    public OrderDto cancelOrder(UUID id) {
        Optional<Order> order = orderRepository.findOrderById(id);
        if (order.isPresent()) {
            order.get().setStatus(OrderStatus.CANCELLED);
            return orderMapper.toDto(orderRepository.save(order.get()));
        } else {
            throw new OrderException("Order not found");
        }
    }
}
