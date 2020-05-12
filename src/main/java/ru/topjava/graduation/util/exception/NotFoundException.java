package ru.topjava.graduation.util.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Requested data not found!");
    }
}