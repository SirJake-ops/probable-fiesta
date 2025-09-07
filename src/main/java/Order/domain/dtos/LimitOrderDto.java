package Order.domain.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public class LimitOrderDto {
    private UUID id;
    private BigDecimal price;
    private BigDecimal quantity;
}
