package com.soaresdev.itautestjr.exceptions;

public class DateTimeAfterNowException extends RuntimeException {
    public DateTimeAfterNowException(String message) {
        super(message);
    }
}