package ru.project.library.mapper;

import org.springframework.stereotype.Service;
import ru.project.library.dto.MyUserDto;
import ru.project.library.entity.MyUser;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserMapper {

    public MyUser toEntity(MyUserDto userDto) {

        MyUser myUser = new MyUser();

        myUser.setUsername(userDto.getUsername());
        myUser.setPassword(userDto.getPassword());
        myUser.setEmail(userDto.getEmail());
        myUser.setRole(userDto.getUserRole());

        return myUser;
    }

    public MyUserDto toDto(MyUser user) {

        MyUserDto dto = new MyUserDto();

        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        dto.setUserRole(user.getRole());

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
