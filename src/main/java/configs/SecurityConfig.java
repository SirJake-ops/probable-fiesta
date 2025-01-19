package configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;

@Configuration
public class SecurityConfig  {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests()
                .requestMatchers("/", "/login**").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard", true)
                .userInfoEndpoint()
                .oidcUserService(oidcUserService());

        return httpSecurity.build();
    }

    @Bean
    public OidcUserService oidcUserService() {
        return new OidcUserService();
    }

}
