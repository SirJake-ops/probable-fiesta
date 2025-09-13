package Order.application.services;

import Order.domain.models.Order;
import Order.enums.Side;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderBookService {

    private final Map<String, OrderBook> orderBooks = new ConcurrentHashMap<String, OrderBook>();

    public OrderBook getOrderBook(String symbol) {
        return orderBooks.computeIfAbsent(symbol, k -> new OrderBook(symbol));
    }

    public void addOrder(Order order) {
        OrderBook book = getOrderBook(order.getSymbol());
        book.addOrder(order);
    }

    public void removeOrder(Order order) {
        OrderBook book = getOrderBook(order.getSymbol());
        book.removeOrder(order);
    }

    public Optional<Order> findBestMatch(Order incomingOrder) {
        OrderBook book = getOrderBook(incomingOrder.getSymbol());
        return book.findBestMatch(incomingOrder);
    }


    private static class OrderBook {
        private final String symbol;
        private final TreeMap<BigDecimal, Queue<Order>> buyOrders = new TreeMap<>(Collections.reverseOrder());
        private final TreeMap<BigDecimal, Queue<Order>> sellOrders = new TreeMap<>();

        private OrderBook(String symbol) {
            this.symbol = symbol;
        }


        public void addOrder(Order order) {
            Map<BigDecimal, Queue<Order>> book = (order.getSide() == Side.BUY) ? buyOrders : sellOrders;
            book.computeIfAbsent(order.getPrice(), p -> new LinkedList<>()).add(order);
        }

        public void removeOrder(Order order) {
           Map<BigDecimal, Queue<Order>> book = (order.getSide() == Side.BUY) ? buyOrders : sellOrders;
           Queue<Order> atPriceOrders = book.get(order.getPrice());

           if (atPriceOrders != null) {
               atPriceOrders.remove();
               if (atPriceOrders.isEmpty()) {
                   book.remove(order.getPrice());
               }
           }
        }

        public Optional<Order> findBestMatch(Order incomingOrder) {
            Map<BigDecimal, Queue<Order>> oppositeBook = (incomingOrder.getSide() == Side.BUY) ? sellOrders : buyOrders;

            for (Map.Entry<BigDecimal, Queue<Order>> entry : oppositeBook.entrySet()) {
                BigDecimal price = entry.getKey();
                Queue<Order> orders = entry.getValue();

                if (canMatch(incomingOrder, price)) {
                    return orders.stream().findFirst();
                }
            }

            return Optional.empty();
        }

        private boolean canMatch(Order incomingOrder, BigDecimal bookPrice) {
            if (incomingOrder.getSide() == Side.BUY) {
                return incomingOrder.getPrice().compareTo(bookPrice) >= 0;
            } else {
                return incomingOrder.getPrice().compareTo(bookPrice) <= 0;
            }
        }
    }
}
