package ru.project.library.service;

import ru.project.library.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<List<AuthorDto>> findAll();

    Optional<AuthorDto> findById(Long id);

    AuthorDto save(AuthorDto authorDto);

    void deleteById(Long id);
}
