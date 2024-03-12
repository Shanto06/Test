package com.polygon.technology.energyMonitoringSystem.repository;

import com.polygon.technology.energyMonitoringSystem.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
//    @Query("SELECT a FROM AdminEntity a WHERE a.name LIKE %:nameEmailPhone% OR a.email LIKE %:nameEmailPhone% OR a.contactNumber " +
//            "LIKE %:nameEmailPhone%")
//
//    SELECT * FROM Energy_Monitoring_System.admins WHERE contact_number LIKE '%017%';

    @Query(nativeQuery = true, value = "SELECT * FROM Energy_Monitoring_System.admins WHERE contact_number LIKE %:nameEmailPhone% or email LIKE %:nameEmailPhone% or name LIKE %:nameEmailPhone%")
    List<AdminEntity> searchByNameEmailPhoneNumber(@Param("nameEmailPhone") String nameEmailPhone);
}

