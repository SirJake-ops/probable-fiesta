package applicationuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService {

    private final ApplicationUserRepository _applicationUserRepository;

    @Autowired
    ApplicationUserService(ApplicationUserRepository applicationUserRepository) {
        this._applicationUserRepository = applicationUserRepository;
    }


}
