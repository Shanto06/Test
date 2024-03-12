package com.polygon.technology.energyMonitoringSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
    private String name;
    private String email;
    private String password;              // Password of the user.
    private String profilePicture;
    private String contactNumber;         // Contact number of the user.
    private Long groupId;
    private String role;             // Role of the user (e.g., ADMIN, STAFF).

}
