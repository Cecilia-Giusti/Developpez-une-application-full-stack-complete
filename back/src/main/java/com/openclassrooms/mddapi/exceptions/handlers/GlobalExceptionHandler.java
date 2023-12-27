package com.openclassrooms.mddapi.exceptions.handlers;


import com.openclassrooms.mddapi.exceptions.AccountException;
import com.openclassrooms.mddapi.exceptions.LoginException;
import com.openclassrooms.mddapi.exceptions.RegisterException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global exception handler for the application.
 * This class provides centralized exception handling across all controllers.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles exceptions related to account operations.
     *
     * @param ex The caught exception.
     * @return A response entity with an error message and HTTP status code.
     */
    @ExceptionHandler(AccountException.class)
    public ResponseEntity<String> handleIncorrectAccountException(AccountException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles exceptions related to registration operations.
     *
     * @param ex The caught exception.
     * @return A response entity with an error message and HTTP status code.
     */
    @ExceptionHandler(RegisterException.class)
    public ResponseEntity<String> handleIncorrectRegisterException(RegisterException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles exceptions related to login operations.
     *
     * @param ex The caught exception.
     * @return A response entity with an error message and HTTP status code.
     */
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<String> handleIncorrectLoginException(LoginException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

}
