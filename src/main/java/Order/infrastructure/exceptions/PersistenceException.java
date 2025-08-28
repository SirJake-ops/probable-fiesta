package Order.infrastructure.exceptions;

public class PersistenceException extends Exception {
    public PersistenceException(String message) {
       super(message);
    }
}
