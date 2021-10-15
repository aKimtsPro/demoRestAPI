package bstorm.akimts.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService service;

    public WebSecurityConfig(UserDetailsService service) {
        this.service = service;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin").password(encoder.encode("pass")).authorities("ADMIN", "USER")
//                .and()
//                .withUser("user").password(encoder.encode("pass")).authorities("USER");

        auth.userDetailsService( service );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.httpBasic();

        http.authorizeRequests()
                // Pour les livres
                .antMatchers(HttpMethod.GET, "/livre/**").hasAuthority("USER")
                .antMatchers("/livre/**").hasAuthority("ADMIN")
                // Pour les auteurs
                .antMatchers(HttpMethod.GET, "/auteur/**").hasAuthority("USER")
                .anyRequest().permitAll();

        // pour H2
        http.headers()
                .frameOptions()
                .disable();
    }
}
