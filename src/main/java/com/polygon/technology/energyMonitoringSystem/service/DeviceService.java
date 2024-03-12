package com.polygon.technology.energyMonitoringSystem.service;

import com.polygon.technology.energyMonitoringSystem.dto.DeviceDto;
import com.polygon.technology.energyMonitoringSystem.entity.DeviceEntity;
import com.polygon.technology.energyMonitoringSystem.exception.DeviceAlreadyExistException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DeviceService {

    public ResponseEntity<Object> createDevice(DeviceDto deviceDto) throws DeviceAlreadyExistException;

    List<DeviceEntity> getAllDevices();

    ResponseEntity<Object> getDeviceById(Long id);

    Long activeDeviceCount(Long groupId);

    Long vendorDeviceCount(Long vendorId);

    ResponseEntity<Object> updateDevice(DeviceDto deviceDto);
}
