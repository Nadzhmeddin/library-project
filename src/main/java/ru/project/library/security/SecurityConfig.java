package ru.project.library.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

@Service
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return http.authorizeHttpRequests(
                auth -> {
                    auth.requestMatchers("/api/v1/users/**").hasAnyRole("ADMIN", "USER");
                    auth.requestMatchers("/api/v1/books/**").hasAnyRole("ADMIN", "USER");
                    auth.requestMatchers("/api/v1/authors/**").hasAnyRole("ADMIN");
                    auth.anyRequest().authenticated();
                }
        )
                .httpBasic(AbstractHttpConfigurer::disable)
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
    public UserDetailsService userDetailsService() {
        UserDetails userUser = User.builder()
                .username("user")
                .password("$2a$12$3ajEN59RfWgmf7GTy/Ova..DQoyscmj2iSPMG/74Af6Bz5SgZ9yWG")
                .roles("USER")
                .build();

        UserDetails adminUser = User.builder()
                .username("admin")
                .password("$2a$12$K4yMKJYiANpiac3LmgUm/OglDetTGwYAsv9V92duxz/nn4dAkIYfO")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(userUser, adminUser);
    }
}
