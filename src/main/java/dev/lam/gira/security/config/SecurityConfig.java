package dev.lam.gira.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth){
//        return auth.setSharedObject(AuthenticationManagerBuilder.class, auth)
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(getPasswordEncoder())
//                .and()
//                .build();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CROSS ORIGIN
        http.cors();

        // Cross-site request forgery
        http.csrf();

        // DISABLE SESSION -> STATELESS
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // JWT FILTER


        // API AUTHENTICATION
        http.antMatcher("/api/v1/**")
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().permitAll();

        return http.build();
    }
}
