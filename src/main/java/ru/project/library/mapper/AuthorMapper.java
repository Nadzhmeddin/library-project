package ru.project.library.mapper;

import org.springframework.stereotype.Service;
import ru.project.library.dto.AuthorDto;
import ru.project.library.entity.Author;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorMapper {

    public Author toEntity(AuthorDto authorDto) {

        Author author = new Author();
        author.setName(authorDto.getName());

        return author;
    }

    public AuthorDto toDto(Author author) {

        AuthorDto dto = new AuthorDto();
        dto.setName(author.getName());

        return dto;
    }

    public List<AuthorDto> toDtoList(List<Author> authors) {
        List<AuthorDto> dtos = new ArrayList<>();
        for (Author author : authors) {
            dtos.add(toDto(author));
        }
        return dtos;
    }
}
