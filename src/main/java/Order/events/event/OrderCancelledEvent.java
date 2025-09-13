package Order.events.event;

import Order.domain.models.Order;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrderCancelledEvent extends ApplicationEvent {
    private final Order order;

    public OrderCancelledEvent(Object source, Order order) {
        super(source);
        this.order = order;
    }
}
