package com.soaresdev.itautestjr.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionsHandler.class);

    @ExceptionHandler(DateTimeAfterNowException.class)
    public ResponseEntity<Void> dateAfterNowException(DateTimeAfterNowException e) {
        logger.error(e.getMessage());
        return ResponseEntity.unprocessableEntity().build();
    }

    @ExceptionHandler(NegativeAmountException.class)
    public ResponseEntity<Void> negativeAmountException(NegativeAmountException e) {
        logger.error(e.getMessage());
        return ResponseEntity.unprocessableEntity().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> illegalArgumentException(IllegalArgumentException e) {
        logger.error(e.getMessage());
        return ResponseEntity.unprocessableEntity().build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Void> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error(e.getMessage());
        return ResponseEntity.badRequest().build();
    }
}