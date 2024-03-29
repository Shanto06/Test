package com.polygon.technology.energyMonitoringSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupDto {
    private String groupName;
    private String groupIcon;
//    private Long customerId;
}
