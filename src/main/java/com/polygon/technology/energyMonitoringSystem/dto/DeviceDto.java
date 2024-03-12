package com.polygon.technology.energyMonitoringSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceDto {
    private Long deviceId;
    private String deviceName;
    private Long groupId;
    private Long vendorId;


}
