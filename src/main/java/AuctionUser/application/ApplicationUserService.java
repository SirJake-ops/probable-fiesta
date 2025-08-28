package AuctionUser.application;

import AuctionUser.infrastructure.repos.ApplicationUserRepositoryAdapter;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService {
    private final ApplicationUserRepositoryAdapter applicationUserRepositoryAdapter;


    public ApplicationUserService(ApplicationUserRepositoryAdapter applicationUserRepositoryAdapter) {
        this.applicationUserRepositoryAdapter = applicationUserRepositoryAdapter;
    }


}
