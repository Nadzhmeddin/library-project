package ru.project.library.mapper;

import org.springframework.stereotype.Service;
import ru.project.library.dto.MyUserDto;
import ru.project.library.entity.MyUser;
import ru.project.library.enums.UserRole;
import ru.project.library.exception.user_exception.IllegalUserRoleException;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserMapper {

    public MyUser toEntity(MyUserDto userDto) {

        MyUser myUser = new MyUser();

        myUser.setUsername(userDto.getUsername());
        myUser.setPassword(userDto.getPassword());
        myUser.setEmail(userDto.getEmail());

        if(userDto.getUserRole().equalsIgnoreCase("user")) {
            myUser.setRole(UserRole.USER);
        }

        else if(userDto.getUserRole().equalsIgnoreCase("admin")) {
            myUser.setRole(UserRole.ADMIN);
        }

        else {
            throw new IllegalUserRoleException("Ошибка ввода роли! Роль может быть либо USER либо ADMIN. Ваш ввод: " + userDto.getUserRole());
        }
        return myUser;
    }

    public MyUserDto toDto(MyUser user) {

        MyUserDto dto = new MyUserDto();

        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        dto.setUserRole(user.getRole().getRole());

        return dto;
    }

    public List<MyUserDto> toDtoList(List<MyUser> users) {
        List<MyUserDto> dtos = new ArrayList<>();

        for (MyUser user : users) {
            dtos.add(toDto(user));
        }

        return dtos;
    }
}
