package com.polygon.technology.energyMonitoringSystem.exception;

/**
 * Custom exception class to indicate that no admins were found.
 */
public class NoAdminsFoundException extends RuntimeException {

    /**
     * Constructs a new NoAdminsFoundException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     */
    public NoAdminsFoundException(String message) {
        super(message);
    }
}
