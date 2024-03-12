package com.polygon.technology.energyMonitoringSystem.controllers;

import com.polygon.technology.energyMonitoringSystem.dto.CustomerDto;
import com.polygon.technology.energyMonitoringSystem.dto.DeviceDto;
import com.polygon.technology.energyMonitoringSystem.entity.CustomerEntity;
import com.polygon.technology.energyMonitoringSystem.entity.DeviceEntity;
import com.polygon.technology.energyMonitoringSystem.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
public class DeviceController {
    private final DeviceService deviceService;

    @PostMapping("/create")
    public ResponseEntity<Object> createDevice(@RequestBody DeviceDto DeviceDto) {
        return deviceService.createDevice(DeviceDto);
    }

    @GetMapping("/all")
    public List<DeviceEntity> getAllDevices()
    {
        return deviceService.getAllDevices();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getDeviceById(@PathVariable Long id) {
        return deviceService.getDeviceById(id);
    }

    @GetMapping("/count/{groupId}")
    public Long activeDeviceCount(@PathVariable Long groupId)
    {
        return deviceService.activeDeviceCount(groupId);
    }

    @GetMapping("/countVendorDevice/{vendorId}")
    public Long vendorDeviceCount(@PathVariable Long vendorId)
    {
        return deviceService.vendorDeviceCount(vendorId);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateDevice(@RequestBody DeviceDto deviceDto) {
        return deviceService.updateDevice(deviceDto);
    }
}
