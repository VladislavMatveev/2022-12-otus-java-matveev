package ru.otus.exceptions;

public class WithdrawException extends RuntimeException{

    public WithdrawException(String message, Throwable cause) {
        super(message, cause);
    }
}
