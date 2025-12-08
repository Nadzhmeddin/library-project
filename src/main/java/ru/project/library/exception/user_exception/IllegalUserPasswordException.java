package ru.project.library.exception.user_exception;

public class IllegalUserPasswordException extends RuntimeException{

    public IllegalUserPasswordException(String message) {
        super(message);
    }
}
