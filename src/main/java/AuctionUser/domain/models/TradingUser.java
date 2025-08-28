package AuctionUser.domain.models;

import AuctionUser.domain.serializers.Email;
import AuctionUser.domain.serializers.PhoneNumber;
import lombok.Getter;
import lombok.Setter;
import shared.common.entities.BaseEntity;
import shared.common.enums.Roles;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "trading_user", uniqueConstraints = {
    @UniqueConstraint(columnNames = "username"),
    @UniqueConstraint(columnNames = "email")
})
public class TradingUser extends BaseEntity {


    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be 3-50 characters")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotBlank(message = "First name is required")
    @Size(max = 100, message = "First name cannot exceed 100 characters")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 100, message = "Last name cannot exceed 100 characters")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Column(name = "password", nullable = false)
    private String password;

    @Valid
    @NotNull(message = "Email is required")
    @Embedded
    private Email email;

    @NotNull(message = "Role is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Roles role;

    @DecimalMin(value = "0.0", message = "Rating cannot be negative")
    @DecimalMax(value = "5.0", message = "Rating cannot exceed 5.0")
    @Column(name = "bid_rating")
    private Double bidRating;

    @NotNull(message = "Account balance is required")
    @DecimalMin(value = "0.0", message = "Account balance cannot be negative")
    @Digits(integer = 10, fraction = 2, message = "Invalid balance format")
    @Column(name = "account_balance", nullable = false, precision = 12, scale = 2)
    private BigDecimal accountBalance;

    @Valid
    @Embedded
    private PhoneNumber phone;

}
