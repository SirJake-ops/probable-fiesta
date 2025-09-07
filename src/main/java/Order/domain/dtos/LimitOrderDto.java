package Order.domain.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class LimitOrderDto {
    private UUID orderId;
    private BigDecimal price;
    private BigDecimal quantity;
}
