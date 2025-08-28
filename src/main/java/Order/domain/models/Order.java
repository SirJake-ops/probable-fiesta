package Order.domain.models;

import Order.enums.OrderType;
import Order.enums.Side;
import jakarta.persistence.*;
import lombok.Getter;
import shared.common.entities.BaseEntity;

@Getter
@Entity
@Table(name = "order")
public class Order extends BaseEntity {
    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "symbol")
    private String symbol;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @Enumerated(EnumType.STRING)
    private Side side;
}
