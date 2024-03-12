package com.polygon.technology.energyMonitoringSystem.service;

import com.polygon.technology.energyMonitoringSystem.dto.AdminDto;
import com.polygon.technology.energyMonitoringSystem.dto.NameEmailPhoneNumberDto;
import com.polygon.technology.energyMonitoringSystem.entity.AdminEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {
    ResponseEntity<Object> createAdmin(AdminDto adminDto);

    List<AdminEntity> getAllAdmins();

    ResponseEntity<Object> getAdminById(Long id);

    ResponseEntity<Object> updateAdmin(Long id, AdminDto adminDto);

    List<AdminEntity> searchByNameEmailPhoneNumber(String nameEmailPhone);
}
