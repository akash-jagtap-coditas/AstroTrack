package com.example.AstroTrack.exception;

/**
 * Exception thrown when a consultation is not found.
 */
public class ConsultationNotFoundException extends RuntimeException {

    /**
     * Constructs a new ConsultationNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public ConsultationNotFoundException(String message) {
        super(message);
    }
}
