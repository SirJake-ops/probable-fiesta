package AuctionUser.domain.serializers;

import AuctionUser.domain.exceptions.PropertyInvalidException;
import lombok.Getter;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class PhoneNumber implements Serializable {

    private String phoneNumber;

    public PhoneNumber() {}
    public PhoneNumber(String phoneNumber) throws PropertyInvalidException {
        if (isValidNumber(phoneNumber.toUpperCase())) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new PropertyInvalidException("Invalid phone number");
        }
    }


    public boolean isValidNumber(String phoneNumber) {
        String regex = "^\\(?\\d{3}\\)?[-\\s]?\\d{3}[-\\s]?\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
