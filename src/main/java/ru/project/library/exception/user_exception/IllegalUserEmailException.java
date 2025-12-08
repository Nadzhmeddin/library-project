package ru.project.library.exception.user_exception;

public class IllegalUserEmailException extends RuntimeException {

    public IllegalUserEmailException(String message) {
        super(message);
    }
}
