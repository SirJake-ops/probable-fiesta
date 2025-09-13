package Order.controller;

import Order.application.services.OrderService;
import Order.domain.dtos.OrderDto;
import Order.domain.mapper.OrderMapper;
import Order.domain.models.Order;
import Order.enums.OrderStatus;
import Order.enums.OrderType;
import Order.enums.Side;
import Order.infrastructure.persistence.OrderRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private OrderService orderService;

    private OrderController orderController;

    @BeforeEach
    void setUp() {
        orderController = new OrderController(orderMapper, orderService);
    }

    @Test
    @DisplayName("Sanity Check")
    public void shouldReturnSanity() {
        Assertions.assertEquals(1, 1);
    }


    @Test
    @DisplayName("Should create an order and return the created order DTO")
    public void shouldCreateOrderAndReturnTheCreatedOrderDTO() {

        OrderDto testOrderDto = OrderDto.builder()
                .price(new BigDecimal("100.00"))
                .quantity(new BigDecimal("2"))
                .symbol("A")
                .orderType(OrderType.MARKET)
                .side(Side.BUY)
                .filledQuantity(new BigDecimal("100"))
                .status(OrderStatus.PENDING)
                .build();

        Order testOrder = Order.builder()
                .price(new BigDecimal("100.00"))
                .quantity(new BigDecimal("2"))
                .symbol("A")
                .orderType(OrderType.MARKET)
                .side(Side.BUY)
                .filledQuantity(new BigDecimal("100"))
                .status(OrderStatus.PENDING)
                .expiresAt(LocalDateTime.of(2026, 10, 10, 10, 10))
                .build();

        Mockito.when(orderService.createOrder(testOrderDto)).thenReturn(testOrderDto);

        ResponseEntity<OrderDto> response = orderController.createOrder(testOrderDto);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(testOrderDto, response.getBody());
    }

    @AfterEach
    void tearDown() {
    }
}