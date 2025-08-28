package Order.enums;

import lombok.Getter;

@Getter
public enum OrderType {
    MARKET("MARKET"),
    LIMIT("LIMIT");

    private final String value;

    OrderType(String value) {
        this.value = value;
    }

}
