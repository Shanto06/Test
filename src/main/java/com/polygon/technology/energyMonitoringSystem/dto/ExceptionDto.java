package com.polygon.technology.energyMonitoringSystem.dto;

import lombok.*;

/**
 * DTO (Data Transfer Object) representing a model for handling exceptions.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionDto {

    private String errorMessage;  // Error message describing the exception.

}
