package applicationuser;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApplicationUserService {
    private final ApplicationUserRepository applicationUserRepository;
}
