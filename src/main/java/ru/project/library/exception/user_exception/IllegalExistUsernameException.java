package ru.project.library.exception.user_exception;

public class IllegalExistUsernameException extends RuntimeException{
    public IllegalExistUsernameException(String message) {
        super(message);
    }
}
