package com.polygon.technology.energyMonitoringSystem.service.Impl;

import com.polygon.technology.energyMonitoringSystem.dto.*;
import com.polygon.technology.energyMonitoringSystem.entity.*;
import com.polygon.technology.energyMonitoringSystem.repository.*;
import com.polygon.technology.energyMonitoringSystem.service.*;
import com.polygon.technology.energyMonitoringSystem.exception.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    @Override
    public ResponseEntity<Object> createVendor(VendorDto vendorDto) {
        VendorEntity vendor = vendorRepository.findByVendorName(vendorDto.getVendorName());
        if(Objects.nonNull(vendor))
        {
            throw new VendorNameAlreadyExistException("This Vendor Name Already Existed");
        }

        VendorEntity vendor1 = VendorEntity.builder()
                .vendorName(vendorDto.getVendorName())
                .vendorIcon(vendorDto.getVendorIcon())
                .vendorEmail(vendorDto.getVendorEmail())
                .vendorContactNumber(vendorDto.getVendorContactNumber())
                .build();

        VendorEntity savedVendor = vendorRepository.save(vendor1);

        return ResponseEntity.ok(savedVendor);
    }

    @Override
    public List<VendorEntity> getAllVendors() {
        List<VendorEntity> vendorEntityList = vendorRepository.findAll();
        if(vendorEntityList.isEmpty())
        {
            throw new NoVendorFoundException("There is no vendors");
        }
        return vendorEntityList;
    }

    @Override
    public ResponseEntity<Object> getVendorById(Long id) {
        Optional<VendorEntity> vendorOptional = vendorRepository.findById(id);
        if(vendorOptional.isEmpty())
        {
            throw new InvalidVendorIdException("Invalid vendor id");
        }
        return ResponseEntity.ok(vendorOptional.get());
    }

    @Override
    public ResponseEntity<Object> updateVendor(Long vendorId, VendorDto vendorDto ) {
        Optional<VendorEntity> vendorOptional = vendorRepository.findById(vendorId);
        if (vendorOptional.isPresent()) {
            VendorEntity vendor = vendorOptional.get();

            vendor.setVendorName(vendorDto.getVendorName());
            vendor.setVendorIcon(vendorDto.getVendorIcon());
            vendor.setVendorEmail(vendorDto.getVendorEmail());
            vendor.setVendorContactNumber(vendorDto.getVendorContactNumber());

            VendorEntity updatedVendor = vendorRepository.save(vendor);

            return ResponseEntity.ok(updatedVendor);
        } else {
            throw new NoVendorFoundException("Vendor not found with ID: " + vendorId);
        }
    }
}
