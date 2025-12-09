package ru.project.library.mapper;

import org.springframework.stereotype.Service;
import ru.project.library.dto.BookDto;
import ru.project.library.entity.Author;
import ru.project.library.entity.Book;
import ru.project.library.entity.MyUser;
import ru.project.library.enums.Genre;
import ru.project.library.exception.book_exception.AuthorNotFoundException;
import ru.project.library.exception.book_exception.GenreNotFoundException;
import ru.project.library.exception.book_exception.UserNotFoundException;
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
            throw new AuthorNotFoundException("Author with that id is not found");
        }
        Optional<MyUser> foundUser = userRepository.findById(bookDto.getUserId());

        if(foundUser.isEmpty()) {
            throw new UserNotFoundException("User with that id is not found!");
        }



        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(foundAuthor.get());
        book.setUser(foundUser.get());
        book.setPublicationYear(bookDto.getPublicationYear());
        book.setStatus(bookDto.getStatus());

        if(bookDto.getGenre().equalsIgnoreCase("fantasy")) {
            book.setGenre(Genre.FANTASY);
        }
        if(bookDto.getGenre().equalsIgnoreCase("detective")) {
            book.setGenre(Genre.DETECTIVE);
        }
        if(bookDto.getGenre().equalsIgnoreCase("historical")) {
            book.setGenre(Genre.HISTORICAL);
        }
        if(bookDto.getGenre().equalsIgnoreCase("scientific")) {
            book.setGenre(Genre.SCIENTIFIC);
        }
        else {
            throw new GenreNotFoundException("Введите один из жанров: fantasy/detective/historical/scientific!\nВаш ввод: " + bookDto.getGenre());
        }


        return book;
    }

    public BookDto toDto(Book book) {
        BookDto dto = new BookDto();

        dto.setTitle(book.getTitle());
        dto.setAuthorId(book.getAuthor().getId());
        dto.setGenre(book.getGenre().getGenre());
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
