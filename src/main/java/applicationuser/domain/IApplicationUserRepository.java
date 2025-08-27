package applicationuser.domain;

import applicationuser.domain.models.ApplicationUser;

import java.util.Optional;

public interface IApplicationUserRepository {
    Optional<ApplicationUser> getUserById(Long id);
    Optional<ApplicationUser> findByUsername(String username);
    Optional<ApplicationUser> findByEmail(String email);
}
