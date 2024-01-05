package com.openclassrooms.mddapi.exceptions.handlers;

import com.openclassrooms.mddapi.exceptions.*;
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
 * This class provides centralized exception handling across all controllers,
 * standardizing the response structure for various types of exceptions.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles exceptions related to validation of request body parameters.
     * This method is invoked when @Valid annotation fails to validate the request body parameters.
     *
     * @param ex      The MethodArgumentNotValidException exception thrown when validation fails.
     * @param headers The HttpHeaders associated with the request.
     * @param status  The HTTP status associated with the exception.
     * @param request The WebRequest in which the exception was raised.
     * @return A ResponseEntity containing the validation error messages.
     */
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

    /**
     * Handles UsernameNotFoundException thrown during authentication.
     *
     * @param ex The UsernameNotFoundException thrown.
     * @return A ResponseEntity with an error message and HTTP status NOT_FOUND.
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles AccountException for issues related to account operations.
     *
     * @param ex The AccountException caught.
     * @return A ResponseEntity with an error message and HTTP status UNAUTHORIZED.
     */
    @ExceptionHandler(AccountException.class)
    public ResponseEntity<String> handleIncorrectAccountException(AccountException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles RegisterException for issues during user registration.
     *
     * @param ex The RegisterException caught.
     * @return A ResponseEntity with an error message and HTTP status CONFLICT.
     */
    @ExceptionHandler(RegisterException.class)
    public ResponseEntity<String> handleIncorrectRegisterException(RegisterException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    /**
     * Handles LoginException for issues during user login.
     *
     * @param ex The LoginException caught.
     * @return A ResponseEntity with an error message and HTTP status UNAUTHORIZED.
     */
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<String> handleIncorrectLoginException(LoginException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles NoSubscribedThemesException when a user tries to access themes to which they are not subscribed.
     *
     * @param ex The NoSubscribedThemesException caught.
     * @return A ResponseEntity with an error message and HTTP status NOT_FOUND.
     */
    @ExceptionHandler(NoSubscribedThemesException.class)
    public ResponseEntity<String> handleNoSubscribedThemesException(NoSubscribedThemesException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles NoArticlesFoundException when no articles are found for a given criteria.
     *
     * @param ex The NoArticlesFoundException caught.
     * @return A ResponseEntity with an error message and HTTP status NOT_FOUND.
     */
    @ExceptionHandler(NoArticlesFoundException.class)
    public ResponseEntity<String> handleNoArticlesFoundException(NoArticlesFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
