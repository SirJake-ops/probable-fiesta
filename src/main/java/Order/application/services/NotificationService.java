package Order.application.services;


import Order.events.event.OrderCancelledEvent;
import Order.events.event.OrderMatchedEvent;
import Order.events.event.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.time.Instant;
import java.util.Map;


@Slf4j
@Service
public class NotificationService {
    private final SimpMessagingTemplate messageTemplate;

    public NotificationService(SimpMessagingTemplate messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    @EventListener
    public void handleOrderMatched(OrderMatchedEvent event) {
        String symbol = event.getBuyOrder().getSymbol();

        messageTemplate.convertAndSendToUser("user", "/topic/trades" + symbol, Map.of(
                "type", "TRADE_EXECUTED",
                "symbol", symbol,
                "price", event.getMatchedPrice(),
                "quantity", event.getMatchedQuantity(),
                "timestamp", Instant.now()));

        log.info("Sent trade notification for symbol: {}", symbol);
    }

    @EventListener
    public void handleOrderPlaced(OrderPlacedEvent event) {
        String symbol = event.getOrder().getSymbol();
        messageTemplate.convertAndSendToUser("user", "/topic/trades", Map.of(
                "type", "ORDER_PLACED",
                "order", event.getOrder(),
                "timestamp", Instant.now()
        ));
    }


    @EventListener
    public void handleOrderCancelled(OrderCancelledEvent event) {
        String symbol = event.getOrder().getSymbol();
        messageTemplate.convertAndSendToUser("user", "/topic/trades", Map.of(
                "type", "ORDER_CANCELLED",
                "orderId", event.getOrder().getId(),
                "timestamp", Instant.now()
        ));
    }
}
