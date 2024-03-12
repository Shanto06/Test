package com.polygon.technology.energyMonitoringSystem.utils;

import com.polygon.technology.energyMonitoringSystem.dto.ExceptionDto;
import com.polygon.technology.energyMonitoringSystem.exception.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * Global exception handler to manage exceptions across the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({UserNotFoundException.class, UserNotActiveException.class, UserAlreadyExistException.class, EmailPasswordNotMatchException.class,
            AdminNotFoundException.class, InvalidTokenException.class, GroupNameAlreadyExistException.class, InvalidGroupIdException.class,
            NoGroupFoundException.class, InvalidGroupAndVendorIdException.class, DeviceAlreadyExistException.class, InvalidVendorIdException.class,
            NoAdminsFoundException.class, NoCustomerFoundException.class, NoDevicesFoundException.class, NoVendorFoundException.class,
            VendorNameAlreadyExistException.class, NoGroupAndVendorFoundException.class})
    public ResponseEntity<Object> returnNotFoundException(Exception ex) {
        if (ex instanceof UserAlreadyExistException) {
            return new ResponseEntity<>(new ExceptionDto(ex.getMessage()), HttpStatus.NOT_IMPLEMENTED);
        } else if (ex instanceof EmailPasswordNotMatchException) {
            return new ResponseEntity<>(new ExceptionDto(ex.getMessage()), HttpStatus.NOT_FOUND);
        } else if (ex instanceof UserNotActiveException || ex instanceof UserNotFoundException) {
            return new ResponseEntity<>(new ExceptionDto(ex.getMessage()), HttpStatus.BAD_REQUEST);
        } else if (ex instanceof AdminNotFoundException) {
            return new ResponseEntity<>(new ExceptionDto(ex.getMessage()), HttpStatus.NOT_FOUND);
        } else if (ex instanceof InvalidTokenException) {
            return new ResponseEntity<>(new ExceptionDto(ex.getMessage()), HttpStatus.NOT_FOUND);
        } else if (ex instanceof GroupNameAlreadyExistException) {
            return new ResponseEntity<>(new ExceptionDto(ex.getMessage()), HttpStatus.NOT_FOUND);
        } else if (ex instanceof InvalidGroupIdException) {
            return new ResponseEntity<>(new ExceptionDto(ex.getMessage()), HttpStatus.NOT_FOUND);
        } else if (ex instanceof NoGroupFoundException) {
            return new ResponseEntity<>(new ExceptionDto(ex.getMessage()), HttpStatus.NOT_FOUND);
        } else if (ex instanceof InvalidGroupAndVendorIdException) {
            return new ResponseEntity<>(new ExceptionDto(ex.getMessage()), HttpStatus.NOT_FOUND);
        } else if (ex instanceof DeviceAlreadyExistException) {
            return new ResponseEntity<>(new ExceptionDto(ex.getMessage()), HttpStatus.NOT_FOUND);
        } else if (ex instanceof InvalidVendorIdException) {
            return new ResponseEntity<>(new ExceptionDto(ex.getMessage()), HttpStatus.NOT_FOUND);
        } else if (ex instanceof NoAdminsFoundException) {
            return new ResponseEntity<>(new ExceptionDto(ex.getMessage()), HttpStatus.NOT_FOUND);
        } else if (ex instanceof NoCustomerFoundException) {
            return new ResponseEntity<>(new ExceptionDto(ex.getMessage()), HttpStatus.NOT_FOUND);
        } else if (ex instanceof NoDevicesFoundException) {
            return new ResponseEntity<>(new ExceptionDto(ex.getMessage()), HttpStatus.NOT_FOUND);
        } else if (ex instanceof NoVendorFoundException) {
            return new ResponseEntity<>(new ExceptionDto(ex.getMessage()), HttpStatus.NOT_FOUND);
        } else if (ex instanceof VendorNameAlreadyExistException) {
            return new ResponseEntity<>(new ExceptionDto(ex.getMessage()), HttpStatus.NOT_FOUND);
        } else if (ex instanceof NoGroupAndVendorFoundException) {
            return new ResponseEntity<>(new ExceptionDto(ex.getMessage()), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(new ExceptionDto(ex.getMessage()), HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
