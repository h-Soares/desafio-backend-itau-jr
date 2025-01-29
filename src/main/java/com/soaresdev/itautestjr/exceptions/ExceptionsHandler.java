package com.soaresdev.itautestjr.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(DateTimeAfterNowException.class)
    public ResponseEntity<Void> dateAfterNowException(DateTimeAfterNowException e) {
        return ResponseEntity.unprocessableEntity().build();
    }

    @ExceptionHandler(NegativeAmountException.class)
    public ResponseEntity<Void> negativeAmountException(NegativeAmountException e) {
        return ResponseEntity.unprocessableEntity().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> illegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.unprocessableEntity().build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Void> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().build();
    }
}