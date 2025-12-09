package ru.project.library.exception.book_exception;

public class BookExistException extends RuntimeException{
    public BookExistException(String message) {
        super(message);
    }
}
