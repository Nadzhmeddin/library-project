package ru.project.library.exception.book_exception;

public class GenreNotFoundException extends RuntimeException{

    public GenreNotFoundException(String message) {
        super(message);
    }
}
