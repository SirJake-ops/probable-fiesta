package Order.domain.dtos;

import Order.enums.OrderStatus;
import Order.enums.OrderType;
import Order.enums.Side;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private UUID id;
    private BigDecimal price;
    private BigDecimal quantity;
    private String symbol;
    private Side side;
    private OrderType orderType;
    private BigDecimal filledQuantity;
    private OrderStatus status;
}
