package Order.events;

import Order.domain.models.Order;
import Order.enums.OrderType;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderMatchingService {

    @EventListener
    public void handleOrderPlaced(OrderPlacedEvent event) {
        Order order = event.getOrder();

        if (order == null) {
            return;
        }
        if (order.getOrderType() == OrderType.MARKET) {
           //TODO: figure out what we are sending out
        } else {
            //TODO: more or less the same as the if above
        }
    }
}
