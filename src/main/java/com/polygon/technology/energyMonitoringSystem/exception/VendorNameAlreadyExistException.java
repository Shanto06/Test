package com.polygon.technology.energyMonitoringSystem.exception;

public class VendorNameAlreadyExistException extends RuntimeException{
    public VendorNameAlreadyExistException(String message)
    {
        super(message);
    }
}
