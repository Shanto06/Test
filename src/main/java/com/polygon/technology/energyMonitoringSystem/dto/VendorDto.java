package com.polygon.technology.energyMonitoringSystem.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendorDto {
    private String vendorName;
    private String vendorIcon;
    private String vendorEmail;
    private String vendorContactNumber;         // Contact number of the user.
}
