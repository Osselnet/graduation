package ru.topjava.graduation.util.exception;

public class ExistsDataException extends RuntimeException {
    public ExistsDataException() {
        super("The same note already exists!");
    }
}