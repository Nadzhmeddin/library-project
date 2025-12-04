package ru.project.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.project.library.dto.BookDto;
import ru.project.library.service.impl.BookServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Optional<List<BookDto>>> findAll() {
        Optional<List<BookDto>> books = bookService.findAll();
        if(books.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(books);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<BookDto>> findById(@PathVariable Long id) {
        Optional<BookDto> foundBook = bookService.findById(id);
        if(foundBook.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(foundBook);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/save")
    public ResponseEntity<BookDto> save(@RequestBody BookDto bookDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(bookDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
