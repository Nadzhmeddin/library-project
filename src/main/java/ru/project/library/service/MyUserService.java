package ru.project.library.service;

import ru.project.library.dto.MyUserDto;
import ru.project.library.entity.MyUser;

import java.util.List;
import java.util.Optional;

public interface MyUserService {

    Optional<List<MyUserDto>> findAll();

    Optional<MyUserDto> findById(Long id);

    MyUserDto saveUser(MyUserDto user);

    void deleteById(Long id);
}
