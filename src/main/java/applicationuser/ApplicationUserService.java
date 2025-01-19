package applicationuser;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApplicationUserService {
    private final ApplicationUserRepository applicationUserRepository;
}
