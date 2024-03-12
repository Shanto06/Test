package com.polygon.technology.energyMonitoringSystem.service;

import com.polygon.technology.energyMonitoringSystem.dto.VendorDto;
import com.polygon.technology.energyMonitoringSystem.entity.VendorEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VendorService {
    ResponseEntity<Object> createVendor(VendorDto vendorDto);

    List<VendorEntity> getAllVendors();

    ResponseEntity<Object> getVendorById(Long id);

    ResponseEntity<Object> updateVendor(Long vendorId, VendorDto vendorDto);
}
