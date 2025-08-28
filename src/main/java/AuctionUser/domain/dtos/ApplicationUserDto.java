package AuctionUser.domain.dtos;

import lombok.Builder;
import lombok.Data;
import shared.common.enums.Roles;

@Builder
@Data
public class ApplicationUserDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Roles role;
}
