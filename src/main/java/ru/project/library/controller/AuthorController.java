package ru.project.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.project.library.dto.AuthorDto;
import ru.project.library.service.impl.AuthorServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private final AuthorServiceImpl authorService;

    public AuthorController(AuthorServiceImpl authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<Optional<List<AuthorDto>>> findAll() {
        Optional<List<AuthorDto>> authors = authorService.findAll();

        if(authors.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(authors);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<AuthorDto>> findById(@PathVariable Long id) {
        Optional<AuthorDto> foundAuthor = authorService.findById(id);
        if(foundAuthor.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(foundAuthor);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/save")
    public ResponseEntity<AuthorDto> save(@RequestBody AuthorDto authorDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.save(authorDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        authorService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
