package com.example.AstroTrack.exception;

import com.example.AstroTrack.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for user-related exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles generic exceptions and returns a response entity with a bad request status.
     *
     * @param exc the exception to handle
     * @return a response entity containing the user exception DTO and bad request status
     */
    @ExceptionHandler
    public ResponseEntity<ExceptionDto> handleException(Exception exc) {
        ExceptionDto error = new ExceptionDto();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles ConsultationListEmpty exceptions and returns a response entity with a not found status.
     *
     * @param exc the exception to handle
     * @return a response entity containing the consultation list empty exception DTO and not found status
     */
    @ExceptionHandler
    public ResponseEntity<ExceptionDto> handleException(ConsultationListEmpty exc) {
        ExceptionDto error = new ExceptionDto();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles ClientNotFoundException and returns a response entity with a not found status.
     *
     * @param exc the exception to handle
     * @return a response entity containing the client not found exception DTO and not found status
     */
    @ExceptionHandler
    public ResponseEntity<ExceptionDto> handleException(ClientNotFoundException exc) {
        ExceptionDto error = new ExceptionDto();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles ConsultationNotFoundException and returns a response entity with a not found status.
     *
     * @param exc the exception to handle
     * @return a response entity containing the consultation not found exception DTO and not found status
     */
    @ExceptionHandler
    public ResponseEntity<ExceptionDto> handleException(ConsultationNotFoundException exc) {
        ExceptionDto error = new ExceptionDto();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
