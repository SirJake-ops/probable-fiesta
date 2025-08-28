package AuctionUser.infrastructure.persistence;


import AuctionUser.domain.models.TradingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ApplicationUserRepository extends JpaRepository<TradingUser, UUID> {
    Optional<TradingUser> findByUsername(String username);
    Optional<TradingUser> findByEmail_Email(String email);
}
