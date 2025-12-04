package ru.project.library.service.impl;

import org.springframework.stereotype.Service;
import ru.project.library.dto.AuthorDto;
import ru.project.library.entity.Author;
import ru.project.library.mapper.AuthorMapper;
import ru.project.library.repository.AuthorRepository;
import ru.project.library.service.AuthorService;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper mapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper mapper) {
        this.authorRepository = authorRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<List<AuthorDto>> findAll() {
        List<Author> authors = authorRepository.findAll();
        if(authors.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(mapper.toDtoList(authors));
    }

    @Override
    public Optional<AuthorDto> findById(Long id) {
        Optional<Author> foundAuthor = authorRepository.findById(id);
        if(foundAuthor.isPresent()) {
            return Optional.of(mapper.toDto(foundAuthor.get()));
        }
        return Optional.empty();
    }

    @Override
    public AuthorDto save(AuthorDto authorDto) {
        List<Author> authors = authorRepository.findAll();
        for (Author author : authors) {
            if(author.getName().equalsIgnoreCase(authorDto.getName())) {
                throw new IllegalArgumentException("Author with that name is exist");
            }
        }
        Author savedAuthor = authorRepository.save(mapper.toEntity(authorDto));
        return mapper.toDto(savedAuthor);
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
