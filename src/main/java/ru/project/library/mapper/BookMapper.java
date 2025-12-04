package ru.project.library.mapper;

import org.springframework.stereotype.Service;
import ru.project.library.dto.BookDto;
import ru.project.library.entity.Author;
import ru.project.library.entity.Book;
import ru.project.library.entity.MyUser;
import ru.project.library.repository.AuthorRepository;
import ru.project.library.repository.MyUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookMapper {

    private final AuthorRepository authorRepository;
    private final MyUserRepository userRepository;

    public BookMapper(AuthorRepository authorRepository, MyUserRepository userRepository) {
        this.authorRepository = authorRepository;
        this.userRepository = userRepository;
    }

    public Book toEntity(BookDto bookDto) {
        Optional<Author> foundAuthor = authorRepository.findById(bookDto.getAuthorId());
        if(foundAuthor.isEmpty()) {
            throw new IllegalArgumentException("Author with that id is not found");
        }

        Optional<MyUser> foundUser = userRepository.findById(bookDto.getUserId());
        if(foundUser.isEmpty()) {
            throw new IllegalArgumentException("User with that id is not found!");
        }

        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(foundAuthor.get());
        book.setGenre(bookDto.getGenre());
        book.setUser(foundUser.get());
        book.setPublicationYear(bookDto.getPublicationYear());
        book.setStatus(bookDto.getStatus());

        return book;
    }

    public BookDto toDto(Book book) {
        BookDto dto = new BookDto();

        dto.setTitle(book.getTitle());
        dto.setAuthorId(book.getAuthor().getId());
        dto.setGenre(book.getGenre());
        dto.setUserId(book.getUser().getId());
        dto.setPublicationYear(book.getPublicationYear());
        dto.setStatus(book.getStatus());

        return dto;
    }

    public List<BookDto> toDtoList(List<Book> books) {
        List<BookDto> dtos = new ArrayList<>();

        for (Book book : books) {
            dtos.add(toDto(book));
        }

        return dtos;
    }


}
