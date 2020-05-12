package ru.topjava.graduation.util.exception;

public class VoteDenyException extends RuntimeException {
    public VoteDenyException() {
        super("Your time for voting is over today! Come back tomorrow!");
    }
}