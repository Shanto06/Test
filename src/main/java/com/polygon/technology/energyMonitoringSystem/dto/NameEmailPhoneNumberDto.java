package com.polygon.technology.energyMonitoringSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NameEmailPhoneNumberDto {
    private String name;                  // Name of the user.
    private String email;                 // Email address of the user.
    private String contactNumber;         // Contact number of the user.
}
