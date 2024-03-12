package com.polygon.technology.energyMonitoringSystem.repository;

import com.polygon.technology.energyMonitoringSystem.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {
    @Query(nativeQuery = true, value = "SELECT COUNT(device_id) FROM Energy_Monitoring_System.devices WHERE is_active = true AND group_id = :groupId")
    Long countDeviceByActiveStatus(Long groupId);

    @Query(nativeQuery = true, value = "SELECT COUNT(device_id) FROM Energy_Monitoring_System.devices WHERE vendor_id = :vendorId")
    Long countDeviceByVendorId(Long vendorId);
}
