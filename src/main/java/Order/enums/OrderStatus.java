package Order.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PENDING("PENDING"),
    PARTIALLY_FILLED("PARTIALLY_FILLED"),
    FILLED("FILLED"),
    CANCELLED("CANCELLED"),
    REJECTED("REJECTED"),
    EXPIRED("EXPIRED");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }
}