package bstorm.akimts.api.config;

import bstorm.akimts.api.security.JwtAuthorizationFilter;
import bstorm.akimts.api.security.JwtProvider;
import bstorm.akimts.api.security.SecurityConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final JwtProvider provider;
    private final UserDetailsService service;

    public WebSecurityConfig(JwtProvider provider, UserDetailsService service) {
        this.provider = provider;
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

        http.addFilterBefore(new JwtAuthorizationFilter(provider), UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.httpBasic();

        http.authorizeRequests()
                // Pour les livres
                .antMatchers(HttpMethod.GET, "/livre/**").hasAuthority("USER")
                .antMatchers("/livre/**").hasAuthority("ADMIN")
                // Pour les auteurs
                .antMatchers(HttpMethod.GET, "/auteur/**").hasAuthority("USER")
                // Pour les utilisateurs
                .antMatchers("/user/**").hasAuthority("ADMIN")
                // Login
                .antMatchers(SecurityConstants.LOGIN_URL).permitAll()
                // Pour le reste
                .anyRequest().authenticated();


        // pour H2
        http.headers()
                .frameOptions()
                .disable();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
