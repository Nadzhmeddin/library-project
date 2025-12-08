package ru.project.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.project.library.dto.MyUserDto;
import ru.project.library.exception.user_exception.IllegalUserPasswordException;
import ru.project.library.service.impl.MyUserServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class MyUserController {

    private final MyUserServiceImpl userService;

    public MyUserController(MyUserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Optional<List<MyUserDto>>> findAll() {
        Optional<List<MyUserDto>> users = userService.findAll();
        if(users.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(users);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<MyUserDto>> findById(@PathVariable Long id) {
        Optional<MyUserDto> foundUser = userService.findById(id);
        if(foundUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(foundUser);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/save")
    public ResponseEntity<MyUserDto> saveUser(@RequestBody MyUserDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
