package com.polygon.technology.energyMonitoringSystem.controllers;

import com.polygon.technology.energyMonitoringSystem.dto.AdminDto;
import com.polygon.technology.energyMonitoringSystem.dto.NameEmailPhoneNumberDto;
import com.polygon.technology.energyMonitoringSystem.entity.AdminEntity;
import com.polygon.technology.energyMonitoringSystem.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/create")
    public ResponseEntity<Object> createAdmin(@RequestBody AdminDto adminDto) {
        return adminService.createAdmin(adminDto);
    }

    @GetMapping("/all")
    public List<AdminEntity> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getAdminById(@PathVariable Long id) {
        return adminService.getAdminById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateAdmin(@PathVariable Long id, @RequestBody AdminDto adminDto) {
        return adminService.updateAdmin(id, adminDto);
    }

    @PostMapping("/SearchByNameEmailPhoneNumber/{nameEmailPhone}")
    public List<AdminEntity> searchByNameEmailPhoneNumber(@PathVariable String nameEmailPhone) {
        return adminService.searchByNameEmailPhoneNumber(nameEmailPhone);
    }

}
