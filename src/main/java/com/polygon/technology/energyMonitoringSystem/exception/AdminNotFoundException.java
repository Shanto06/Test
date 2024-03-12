package com.polygon.technology.energyMonitoringSystem.exception;

/**
 * Custom exception class to indicate that an admin entity is not found in the system.
 */
public class AdminNotFoundException extends RuntimeException {

    /**
     * Constructs a new AdminNotFoundException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method).
     */
    public AdminNotFoundException(String message) {
        super(message);
    }
}
