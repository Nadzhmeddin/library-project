package ru.project.library.service;

import ru.project.library.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<List<BookDto>> findAll();

    Optional<BookDto> findById(Long id);

    BookDto save(BookDto bookDto);

    void deleteById(Long id);
}
