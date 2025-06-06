package OAuthUser;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;


@Log4j2
@RestController
@RequestMapping("api/v1/login")
public class OAuthUserController {


    @GetMapping
    public Map<String, String> user(@AuthenticationPrincipal OAuth2User principal) {
       return Collections.singletonMap("name", principal.getAttribute("name"));
    }

}
