package ru.project.library.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.project.library.entity.MyUser;
import ru.project.library.repository.MyUserRepository;

import java.util.Optional;

@Service
public class MyUserDetails implements UserDetailsService {

    private final MyUserRepository repository;

    public MyUserDetails(MyUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> myUserByUsername = repository.findMyUserByUsername(username);
        if(myUserByUsername.isPresent()) {
            MyUser myUser = myUserByUsername.get();
            return User.builder()
                    .username(myUser.getUsername())
                    .password(myUser.getPassword())
                    .roles(myUser.getRole().getRole())
                    .build();
        } else throw new UsernameNotFoundException("User not found: " + username);
    }
}
