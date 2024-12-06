package com.example.AstroTrack.exception;

/**
 * Exception thrown when the consultation list is empty.
 */
public class ConsultationListEmpty extends RuntimeException {

    /**
     * Constructs a new ConsultationListEmpty exception with the specified detail message.
     *
     * @param message the detail message
     */
    public ConsultationListEmpty(String message) {
        super(message);
    }
}
