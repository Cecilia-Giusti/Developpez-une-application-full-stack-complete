package com.openclassrooms.mddapi.exceptions.handlers;


import com.openclassrooms.mddapi.exceptions.AccountException;
import com.openclassrooms.mddapi.exceptions.LoginException;
import com.openclassrooms.mddapi.exceptions.NoSubscribedThemesException;
import com.openclassrooms.mddapi.exceptions.RegisterException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global exception handler for the application.
 * This class provides centralized exception handling across all controllers.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        StringBuilder errors = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            errors.append(errorMessage).append("\n");
        });

        return new ResponseEntity<>(errors.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

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
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
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

    @ExceptionHandler(NoSubscribedThemesException.class)
    public ResponseEntity<String> handleNoSubscribedThemesException(NoSubscribedThemesException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
