package ru.project.library.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.project.library.dto.MyUserDto;
import ru.project.library.entity.MyUser;
import ru.project.library.exception.user_exception.IllegalExistUsernameException;
import ru.project.library.exception.user_exception.IllegalUserPasswordException;
import ru.project.library.exception.user_exception.IllegalUserUsernameException;
import ru.project.library.exception.user_exception.IllegalUserEmailException;
import ru.project.library.mapper.MyUserMapper;
import ru.project.library.repository.MyUserRepository;
import ru.project.library.service.MyUserService;

import java.util.List;
import java.util.Optional;

@Service
public class MyUserServiceImpl implements MyUserService {

    private final MyUserRepository repository;
    private final MyUserMapper mapper;
    private final PasswordEncoder passwordEncoder;


    public MyUserServiceImpl(MyUserRepository repository, MyUserMapper mapper, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<List<MyUserDto>> findAll() {
        List<MyUser> users = repository.findAll();
        if(users.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(mapper.toDtoList(users));
    }

    @Override
    public Optional<MyUserDto> findById(Long id) {
        Optional<MyUser> foundUser = repository.findById(id);
        if(foundUser.isPresent()) {
            return Optional.of(mapper.toDto(foundUser.get()));
        }
        else return Optional.empty();
    }

    @Override
    public MyUserDto saveUser(MyUserDto dto) {
        List<MyUser> users = repository.findAll();
        for (MyUser user : users) {
            if(user.getUsername().equalsIgnoreCase(dto.getUsername())) {
                throw new IllegalExistUsernameException("Данный пользователь с таким username существует! Ваш ввод: " + dto.getUsername());
            }
        }

        if(!dto.getEmail().contains("@")) {
            throw new IllegalUserEmailException("Ошибка ввода email: " + dto.getEmail());
        }
        if(dto.getPassword().isEmpty()) {
            throw new IllegalUserPasswordException("Поле password не может быть пустым!");
        }
        if(dto.getUsername().isEmpty()) {
            throw new IllegalUserUsernameException("Поле username не может быть пустым!");
        }



        MyUser entity = mapper.toEntity(dto);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        MyUser savedEntity = repository.save(entity);

        return mapper.toDto(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
