package AuctionUser.controller;

import AuctionUser.application.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1")
public class ApplicationUserController {
    private ApplicationUserService applicationUserService;

    @Autowired
    ApplicationUserController(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }


}
