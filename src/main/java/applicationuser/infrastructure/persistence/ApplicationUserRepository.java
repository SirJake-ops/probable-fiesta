package applicationuser.infrastructure.persistence;


import applicationuser.domain.models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findApplicationUserByUsername(String username);
    Optional<ApplicationUser> findApplicationUserByEmail(String email);
}
