package Order.events.event;

import Order.domain.models.Order;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.math.BigDecimal;

@Getter
public class OrderMatchedEvent extends ApplicationEvent {
    private final Order buyOrder;
    private final Order sellOrder;
    private final BigDecimal matchedPrice;
    private final BigDecimal matchedQuantity;


    public OrderMatchedEvent(Object source, Order buyOrder, Order sellOrder, BigDecimal matchedPrice, BigDecimal matchedQuantity) {
        super(source);
        this.buyOrder = buyOrder;
        this.sellOrder = sellOrder;
        this.matchedPrice = matchedPrice;
        this.matchedQuantity = matchedQuantity;
    }
}
