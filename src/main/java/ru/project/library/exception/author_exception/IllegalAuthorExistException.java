package ru.project.library.exception.author_exception;

public class IllegalAuthorExistException extends RuntimeException {

    public IllegalAuthorExistException(String message) {
        super(message);
    }
}
