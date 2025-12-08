package ru.project.library.exception.user_exception;

public class IllegalUserRoleException extends RuntimeException {

    public IllegalUserRoleException(String message) {
        super(message);
    }
}
