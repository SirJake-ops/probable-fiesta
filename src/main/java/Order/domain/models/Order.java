package Order.domain.models;

import Order.enums.OrderStatus;
import Order.enums.OrderType;
import Order.enums.Side;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import shared.common.entities.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@Table(name = "trading_order", indexes = {
    @Index(name = "idx_symbol_side", columnList = "symbol, side"),
    @Index(name = "idx_created_date", columnList = "created_date")
})
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Digits(integer = 10, fraction = 8, message = "Invalid price format")
    @Column(name = "price", nullable = false, precision = 18, scale = 8)
    private BigDecimal price;

    @NotNull(message = "Quantity is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Quantity must be greater than 0")
    @Digits(integer = 10, fraction = 8, message = "Invalid quantity format")
    @Column(name = "quantity", nullable = false, precision = 18, scale = 8)
    private BigDecimal quantity;

    @NotBlank(message = "Trading symbol is required")
    @Size(min = 2, max = 20, message = "Symbol must be 2-20 characters")
    @Pattern(regexp = "^[A-Z0-9/]+$", message = "Symbol must contain only uppercase letters, numbers, and forward slash")
    @Column(name = "symbol", nullable = false, length = 20)
    private String symbol;

    @NotNull(message = "Order type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "order_type", nullable = false)
    private OrderType orderType;

    @NotNull(message = "Order side is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "side", nullable = false)
    private Side side;

    @Column(name = "filled_quantity", precision = 18, scale = 8)
    private BigDecimal filledQuantity = BigDecimal.ZERO;

    @NotNull(message = "Order status is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status = OrderStatus.PENDING;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    public BigDecimal getRemainingQuantity() {
        return quantity.subtract(filledQuantity != null ? filledQuantity : BigDecimal.ZERO);
    }

    public boolean isFullyFilled() {
        return filledQuantity != null && filledQuantity.compareTo(quantity) >= 0;
    }

    public boolean isPartiallyFilled() {
        return filledQuantity != null && filledQuantity.compareTo(BigDecimal.ZERO) > 0 && !isFullyFilled();
    }
}
