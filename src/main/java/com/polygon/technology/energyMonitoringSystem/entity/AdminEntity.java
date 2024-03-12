package com.polygon.technology.energyMonitoringSystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "admins")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminEntity {
    @Id
    private Long userId;             // Unique identifier for the admin user.
    private String name;                  // Name of the user.
    @Column(nullable = false, unique = true)
    private String email;                 // Email address of the user.
    private String password;              // Password of the user.
    private String contactNumber;         // Contact number of the user.
    private String profilePicture;
    private String bloodGroup;
    private String designation;           // Designation or role of the user.
    private String emergencyContactNumber;         // Contact number of the user.
    private String nidFrontPicture;
    private String nidBackPicture;
    private boolean isActive;             // Active status of the user.
}
