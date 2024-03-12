package com.polygon.technology.energyMonitoringSystem.service.Impl;

import com.polygon.technology.energyMonitoringSystem.dto.*;
import com.polygon.technology.energyMonitoringSystem.entity.*;
import com.polygon.technology.energyMonitoringSystem.exception.*;
import com.polygon.technology.energyMonitoringSystem.repository.*;
import com.polygon.technology.energyMonitoringSystem.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;
    private final GroupRepository groupRepository;
    private final VendorRepository vendorRepository;


    @Override
    public ResponseEntity<Object> createDevice(DeviceDto deviceDto) {

        Optional<DeviceEntity> device = deviceRepository.findById(deviceDto.getDeviceId());

        Optional<GroupEntity> groupOptional = groupRepository.findById(deviceDto.getGroupId());
        Optional<VendorEntity> vendorOptional = vendorRepository.findById(deviceDto.getVendorId());


        if(device.isEmpty())
        {
            if(groupOptional.isEmpty() || vendorOptional.isEmpty())
            {
                throw new InvalidGroupAndVendorIdException("Invalid group or Vendor id");
            } else
            {
                GroupEntity group1 = groupOptional.get();
                VendorEntity vendor1 = vendorOptional.get();

               DeviceEntity device1 = DeviceEntity.builder()
                       .deviceId(deviceDto.getDeviceId())
                       .deviceName(deviceDto.getDeviceName())
                       .group(group1)
                       .vendor(vendor1)
                       .isActive(true)
                       .build();

               DeviceEntity savedDevice = deviceRepository.save(device1);

               return ResponseEntity.ok(savedDevice);
            }
        }
        else
        {
            throw new DeviceAlreadyExistException("Device already existed");
        }
    }

    @Override
    public List<DeviceEntity> getAllDevices() {
        List<DeviceEntity> device = deviceRepository.findAll();
        if(device.isEmpty())
        {
            throw new NoDevicesFoundException("There is no device");
        }
        return device;
    }

    @Override
    public ResponseEntity<Object> getDeviceById(Long id) {
        Optional<DeviceEntity> device = deviceRepository.findById(id);
        if(device.isEmpty())
        {
            throw new InvalidDeviceIdException("Device id is not valid");
        }
        return ResponseEntity.ok(device.get());
    }

    @Override
    public Long activeDeviceCount(Long groupId) {
        Optional<GroupEntity> groups = groupRepository.findById(groupId);
        if (groups.isEmpty()) {
            throw new InvalidGroupIdException("Group id is not valid");
        }
        return deviceRepository.countDeviceByActiveStatus(groupId);
    }

    @Override
    public Long vendorDeviceCount(Long vendorId) {
        Optional<VendorEntity> vendors = vendorRepository.findById(vendorId);
        if (vendors.isEmpty()) {
            throw new InvalidVendorIdException("Vendor id is not valid");
        }
        return deviceRepository.countDeviceByVendorId(vendorId);
    }

    @Override
    public ResponseEntity<Object> updateDevice(DeviceDto deviceDto) {
        Optional<DeviceEntity> deviceOptional = deviceRepository.findById(deviceDto.getDeviceId());
        Optional<GroupEntity> groupOptional = groupRepository.findById(deviceDto.getGroupId());
        Optional<VendorEntity> vendorOptional = vendorRepository.findById(deviceDto.getVendorId());

        if (groupOptional.isPresent() && vendorOptional.isPresent()) {
            if (deviceOptional.isPresent()) {
                DeviceEntity device = deviceOptional.get();
                GroupEntity group1 = groupOptional.get();
                VendorEntity vendor1 = vendorOptional.get();

                device.setDeviceId(deviceDto.getDeviceId());
                device.setDeviceName(deviceDto.getDeviceName());
                device.setGroup(group1);
                device.setVendor(vendor1);

                DeviceEntity updatedDevice = deviceRepository.save(device);

                return ResponseEntity.ok(updatedDevice);
            } else {
                throw new NoDevicesFoundException("Device not found with ID");
            }
        } else {
            throw new NoGroupAndVendorFoundException("Group or Vendor not found with ID");
        }
    }
}
