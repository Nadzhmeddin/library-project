package ru.project.library.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.library.entity.UserForm;
import ru.project.library.jwt.JwtService;
import ru.project.library.security.MyUserDetailsService;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private MyUserDetailsService myUserDetailsService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, MyUserDetailsService myUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.myUserDetailsService = myUserDetailsService;
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody UserForm userForm) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userForm.username(), userForm.password()
        ));
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(myUserDetailsService.loadUserByUsername(userForm.username()));
        } else {
            throw new UsernameNotFoundException("Username not found!");
        }
    }
}
