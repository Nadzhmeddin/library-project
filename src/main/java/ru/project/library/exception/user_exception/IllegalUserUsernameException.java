package ru.project.library.exception.user_exception;

public class IllegalUserUsernameException extends RuntimeException {

    public IllegalUserUsernameException(String message) {
        super(message);
    }
}
