package Order.controller;

import Order.application.OrderService;
import Order.application.exceptions.OrderException;
import Order.domain.dtos.OrderDto;
import Order.domain.mapper.OrderMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.UUID;

@Controller
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderMapper orderMapper;
    private final OrderService orderService;

    public OrderController(OrderMapper orderMapper, OrderService orderService) {
        this.orderMapper = orderMapper;
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto orderDto) {
        try {
            OrderDto savedOrder = orderService.createOrder(orderDto);
            return ResponseEntity.ok(savedOrder);
        } catch (OrderException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}/modify")
    public ResponseEntity<OrderDto> updateOrder(@Valid @RequestBody OrderDto orderDto, @PathVariable UUID id) {
        try {
            OrderDto updatedOrder = orderService.updateOrder(orderDto, id);
            return ResponseEntity.ok(updatedOrder);
        } catch (OrderException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<OrderDto> cancelOrder(@PathVariable UUID id) {
        try {
            OrderDto canceledOrder = orderService.cancelOrder(id);
            return ResponseEntity.ok(canceledOrder);
        } catch (OrderException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
