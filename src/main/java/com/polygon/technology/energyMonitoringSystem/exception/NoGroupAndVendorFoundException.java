package com.polygon.technology.energyMonitoringSystem.exception;

public class NoGroupAndVendorFoundException extends RuntimeException{
    public NoGroupAndVendorFoundException(String message)
    {
        super(message);
    }
}
