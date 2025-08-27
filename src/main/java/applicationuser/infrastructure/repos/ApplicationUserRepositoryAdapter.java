package applicationuser.infrastructure.repos;

import applicationuser.domain.IApplicationUserRepository;
import applicationuser.domain.models.ApplicationUser;
import applicationuser.infrastructure.persistence.ApplicationUserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApplicationUserRepositoryAdapter implements IApplicationUserRepository {

    private final ApplicationUserRepository applicationUserRepository;

    public ApplicationUserRepositoryAdapter(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public Optional<ApplicationUser> getUserById(Long id) {
        return applicationUserRepository.findById(id);
    }

    @Override
    public Optional<ApplicationUser> findByUsername(String username) {
        return applicationUserRepository.findApplicationUserByUsername(username);
    }

    @Override
    public Optional<ApplicationUser> findByEmail(String email) {
        return applicationUserRepository.findApplicationUserByEmail(email);
    }
}
