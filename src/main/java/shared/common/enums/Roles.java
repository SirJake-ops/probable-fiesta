package shared.common.enums;

import lombok.Getter;

@Getter
public enum Roles {
    ADMIN("ADMIN"),
    SUPERINTENDENT("SUPERINTENDENT"),
    SHIP_OFFICER("SHIP_OFFICER"),
    VENDOR("VENDOR");


    private final String value;

    Roles(String value) {
        this.value = value;
    }

}
