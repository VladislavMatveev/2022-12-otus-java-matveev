package ru.otus.exceptions;

public class UnknownBanknoteException extends RuntimeException{
    public UnknownBanknoteException(String message) {
        super(message);
    }
}
