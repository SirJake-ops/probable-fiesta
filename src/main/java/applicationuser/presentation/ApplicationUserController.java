package applicationuser.presentation;

import applicationuser.application.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("api/v1")
public class ApplicationUserController {

    private ApplicationUserService applicationUserService;


    @Autowired
    ApplicationUserController(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    @RequestMapping("/user")
    public Principal getUser(Principal user){
        return user;
    }
}
