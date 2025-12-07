package ru.project.library.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

@Service
@Configuration
public class SecurityConfig {

    private final MyUserDetails myUserDetails;

    public SecurityConfig(MyUserDetails myUserDetails) {
        this.myUserDetails = myUserDetails;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return http.authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/api/v1/users/**").hasAnyRole("ADMIN", "USER");
                    auth.requestMatchers("/api/v1/books/**").hasAnyRole("ADMIN", "USER");
                    auth.requestMatchers("/api/v1/authors/**").hasAnyRole("ADMIN");
                    auth.anyRequest().authenticated();
        })
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(myUserDetails)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
}
