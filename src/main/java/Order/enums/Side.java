package Order.enums;

import lombok.Getter;

@Getter
public enum Side {
    BUY("BUY"),
    SELL("SELL");

    private final String value;

    private Side(String value) {
        this.value = value;
    }

}
