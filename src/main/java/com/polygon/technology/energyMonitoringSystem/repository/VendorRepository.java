package com.polygon.technology.energyMonitoringSystem.repository;

import com.polygon.technology.energyMonitoringSystem.entity.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<VendorEntity, Long> {
    VendorEntity findByVendorName(String name);
}
