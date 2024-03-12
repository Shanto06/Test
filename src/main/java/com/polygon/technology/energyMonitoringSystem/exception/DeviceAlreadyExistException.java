package com.polygon.technology.energyMonitoringSystem.exception;

public class DeviceAlreadyExistException extends RuntimeException {
    public DeviceAlreadyExistException(String message) {
        super(message);
    }
}
