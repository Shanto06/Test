package com.polygon.technology.energyMonitoringSystem.controllers;

import com.polygon.technology.energyMonitoringSystem.dto.GroupDto;
import com.polygon.technology.energyMonitoringSystem.dto.VendorDto;
import com.polygon.technology.energyMonitoringSystem.entity.GroupEntity;
import com.polygon.technology.energyMonitoringSystem.entity.VendorEntity;
import com.polygon.technology.energyMonitoringSystem.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendors")
@RequiredArgsConstructor
public class VendorController {

    private final VendorService vendorService;

    @PostMapping("/create")
    public ResponseEntity<Object> createVendor(@RequestBody VendorDto vendorDto) {
        return vendorService.createVendor(vendorDto);
    }

    @GetMapping("all")
    public List<VendorEntity> getAllVendors()
    {
        return vendorService.getAllVendors();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getVendorById(@PathVariable Long id) {
        return vendorService.getVendorById(id);
    }

    @PutMapping("/update/{vendorId}")
    public ResponseEntity<Object> updateVendor(@PathVariable Long vendorId, @RequestBody VendorDto vendorDto) {
        return vendorService.updateVendor(vendorId, vendorDto);
    }
}
