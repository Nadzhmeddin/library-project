package ru.project.library.exception.author_exception;

public class IllegalAuthorNameException extends RuntimeException{

    public IllegalAuthorNameException(String message) {
        super(message);
    }
}
