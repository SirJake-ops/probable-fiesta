package AuctionUser.domain.exceptions;

public class PropertyInvalidException extends RuntimeException {
    public PropertyInvalidException(String message){
        super(message);
    }
}
