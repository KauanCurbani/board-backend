package com.curbanii.board.application.Config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomException {
    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<ExceptionMessage> UserNotFoundException(BadCredentialsException ex, WebRequest request) {
        ExceptionMessage error = new ExceptionMessage("Bad Credentials", ex.getMessage());
        return new ResponseEntity<ExceptionMessage>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionMessage> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionMessage error = new ExceptionMessage("Server Error", ex.getMessage());
        return new ResponseEntity<ExceptionMessage>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<ExceptionMessage> handleAllExceptions(RuntimeException ex, WebRequest request) {
        ExceptionMessage error = new ExceptionMessage("Server Error", ex.getMessage());
        return new ResponseEntity<ExceptionMessage>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<ExceptionMessage> handleAllExceptions(IllegalArgumentException ex, WebRequest request) {
        ExceptionMessage error = new ExceptionMessage("Bad Request", ex.getMessage());
        return new ResponseEntity<ExceptionMessage>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public final ResponseEntity<ExceptionMessage> handleAllExceptions(NullPointerException ex, WebRequest request) {
        ExceptionMessage error = new ExceptionMessage("Null Pointer Exception", ex.getMessage());
        return new ResponseEntity<ExceptionMessage>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalStateException.class)
    public final ResponseEntity<ExceptionMessage> handleAllExceptions(IllegalStateException ex, WebRequest request) {
        ExceptionMessage error = new ExceptionMessage("Illegal State Exception", ex.getMessage());
        return new ResponseEntity<ExceptionMessage>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public final ResponseEntity<ExceptionMessage> handleAllExceptions(UnsupportedOperationException ex, WebRequest request) {
        ExceptionMessage error = new ExceptionMessage("Unsupported Operation Exception", ex.getMessage());
        return new ResponseEntity<ExceptionMessage>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
