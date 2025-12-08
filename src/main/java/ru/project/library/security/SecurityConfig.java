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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;
import ru.project.library.jwt.JwtAuthenticationFilter;

@Service
@Configuration
public class SecurityConfig {

    private final MyUserDetailsService myUserDetailsService;
    private final JwtAuthenticationFilter authenticationFilter;

    public SecurityConfig(MyUserDetailsService myUserDetailsService, JwtAuthenticationFilter authenticationFilter) {
        this.myUserDetailsService = myUserDetailsService;
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/api/v1/authenticate").permitAll();
            auth.requestMatchers("/api/v1/users/save").permitAll();
            auth.requestMatchers("/api/v1/users/delete/", "/api/v1/users/find/", "/api/v1/users").hasAnyRole("ADMIN", "USER");
            auth.requestMatchers("/api/v1/books/**").hasAnyRole("ADMIN", "USER");
            auth.requestMatchers("/api/v1/authors/**").hasAnyRole("ADMIN", "USER");
            auth.anyRequest().authenticated();
        })
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
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
                .userDetailsService(myUserDetailsService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
}
