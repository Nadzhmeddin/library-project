package ru.project.library.service.impl;

import org.springframework.stereotype.Service;
import ru.project.library.dto.BookDto;
import ru.project.library.entity.Book;
import ru.project.library.exception.book_exception.BookPublicationException;
import ru.project.library.exception.book_exception.BookTitleNullException;
import ru.project.library.exception.book_exception.BookExistException;
import ru.project.library.mapper.BookMapper;
import ru.project.library.repository.BookRepository;
import ru.project.library.service.BookService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper mapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper mapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<List<BookDto>> findAll() {
        List<Book> books = bookRepository.findAll();
        if(books.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(mapper.toDtoList(books));
    }

    @Override
    public Optional<BookDto> findById(Long id) {
        Optional<Book> foundBook = bookRepository.findById(id);
        if(foundBook.isPresent()) {
            return Optional.of(mapper.toDto(foundBook.get()));
        }
        return Optional.empty();
    }

    @Override
    public BookDto save(BookDto bookDto) {
        List<Book> books = bookRepository.findAll();
        for (Book book : books) {
            if(book.getTitle().equalsIgnoreCase(bookDto.getTitle()) && book.getAuthor().getId().equals(bookDto.getAuthorId())) {
                throw new BookExistException("This book already exists in the database");
            }
        }


        if(bookDto.getPublicationYear() > LocalDateTime.now().getYear()) {
            throw new BookPublicationException("Дата издания книги превышает текущую дату! Ваш ввод: " + bookDto.getPublicationYear());
        }

        if(bookDto.getTitle().isEmpty()) {
            throw new BookTitleNullException("Название книги не может быть пустым!");
        }
        Book savedBook = bookRepository.save(mapper.toEntity(bookDto));
        return mapper.toDto(savedBook);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
