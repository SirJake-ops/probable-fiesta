package shared.common.enums;

public enum Roles {
    ADMIN("ADMIN"),
    USER("USER");


    private final String value;

    Roles(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
