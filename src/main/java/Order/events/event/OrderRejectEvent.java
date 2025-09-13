package Order.events.event;

import Order.domain.models.Order;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrderRejectEvent extends ApplicationEvent {
    private final Order order;
    private final String reason;

    public OrderRejectEvent(Object source, Order order, String reason) {
        super(source);
        this.order = order;
        this.reason = reason;
    }
}
