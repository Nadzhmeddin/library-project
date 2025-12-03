package ru.project.library.service.impl;

import org.springframework.stereotype.Service;
import ru.project.library.dto.MyUserDto;
import ru.project.library.entity.MyUser;
import ru.project.library.mapper.MyUserMapper;
import ru.project.library.repository.MyUserRepository;
import ru.project.library.service.MyUserService;

import java.util.List;
import java.util.Optional;

@Service
public class MyUserServiceImpl implements MyUserService {

    private final MyUserRepository repository;
    private final MyUserMapper mapper;

    public MyUserServiceImpl(MyUserRepository repository, MyUserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
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
                throw new IllegalArgumentException("Данный пользователь с таким username существует!");
            }
        }

        MyUser entity = mapper.toEntity(dto);
        MyUser savedEntity = repository.save(entity);

        return mapper.toDto(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
