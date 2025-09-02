package Order.application.exceptions;

public class OrderException extends RuntimeException{
    public OrderException(String message) {
        super(message);
    }
}
