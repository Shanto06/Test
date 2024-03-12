package com.polygon.technology.energyMonitoringSystem.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminDto {
    private String name;                  // Name of the user.
    private String email;                 // Email address of the user.
    private String password;         // Password for the user.
    private String contactNumber;         // Contact number of the user.
    private String profilePicture;
    private String bloodGroup;
    private String designation;           // Designation or role of the user.
    private String emergencyContactNumber;         // Contact number of the user.
    private String nidFrontPicture;
    private String nidBackPicture;
}
