package Order.application.services;

import AuctionUser.domain.models.TradingUser;
import AuctionUser.infrastructure.persistence.ApplicationUserRepository;
import Order.application.exceptions.OrderException;
import Order.domain.models.Order;
import Order.enums.Side;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class UserBalanceService {
    private final ApplicationUserRepository applicationUserRepository;

    public UserBalanceService(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Transactional
    void processTradeSettlement(Order incomingOrder, Order bookOrder, BigDecimal tradePrice, BigDecimal tradeQuantity) {
        BigDecimal totalAmount = tradeQuantity.multiply(tradePrice);

        TradingUser buyer = getUserForOrder(incomingOrder.getSide() == Side.BUY ? incomingOrder : bookOrder);
        buyer.setAccountBalance(buyer.getAccountBalance().subtract(totalAmount));
        applicationUserRepository.save(buyer);

        TradingUser seller = getUserForOrder(bookOrder.getSide() == Side.SELL ? bookOrder : incomingOrder);
        seller.setAccountBalance(seller.getAccountBalance().add(totalAmount));
        applicationUserRepository.save(seller);
    }

    private TradingUser getUserForOrder(Order order) {
       return applicationUserRepository.findById(order.getId()) .orElseThrow(() -> new OrderException("User cannot be found."));
    }
}
