package com.polygon.technology.energyMonitoringSystem.dto;

import lombok.*;

/**
 * DTO (Data Transfer Object) representing the request model for creating or updating a user.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String name;                  // Name of the user.
    private String email;                 // Email address of the user.
    private String contactNumber;         // Contact number of the user.
    private String profilePicture;
    private String bloodGroup;
    private String designation;           // Designation or role of the user.
    private String emergencyContactNumber;         // Contact number of the user.
    private String nidFrontPicture;
    private String nidBackPicture;
    private String password;         // Password for the user.
    private Long groupId;


    private String reseatPassword;   // New password for resetting the user's password.
    private String role;             // Role of the user (e.g., ADMIN, STAFF).

}
