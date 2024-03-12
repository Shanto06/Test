package com.polygon.technology.energyMonitoringSystem.service.Impl;

import com.polygon.technology.energyMonitoringSystem.dto.AdminDto;
import com.polygon.technology.energyMonitoringSystem.dto.*;
import com.polygon.technology.energyMonitoringSystem.entity.AdminEntity;
import com.polygon.technology.energyMonitoringSystem.entity.UserEntity;
import com.polygon.technology.energyMonitoringSystem.exception.*;
import com.polygon.technology.energyMonitoringSystem.repository.AdminRepository;
import com.polygon.technology.energyMonitoringSystem.repository.UserRepository;
import com.polygon.technology.energyMonitoringSystem.service.AdminService;
import com.polygon.technology.energyMonitoringSystem.service.UserService;
import com.polygon.technology.energyMonitoringSystem.utils.Role;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<Object> createAdmin(AdminDto adminDto) {
        UserEntity user = userRepository.findByEmail(adminDto.getEmail());
        if(user != null)
        {
            throw new UserAlreadyExistException("This Email Already Existed");
        }
        UserDto user1 = UserDto.builder()
                .name(adminDto.getName())
                .email(adminDto.getEmail())
                .contactNumber(adminDto.getContactNumber())
                .profilePicture(adminDto.getProfilePicture())
                .bloodGroup(adminDto.getBloodGroup())
                .designation(adminDto.getDesignation())
                .emergencyContactNumber(adminDto.getEmergencyContactNumber())
                .nidFrontPicture(adminDto.getNidFrontPicture())
                .nidBackPicture(adminDto.getNidBackPicture())
                .password(adminDto.getPassword())
                .role(Role.ADMIN.name())
                .build();
        Map<String, Object> register = userService.register(user1);
        Long userId = (Long) register.get("savedUserId");


        AdminEntity admin = AdminEntity.builder()
                .userId(userId)
                .isActive(true)
                .name(adminDto.getName())
                .email(adminDto.getEmail())
                .password(adminDto.getPassword())
                .contactNumber(adminDto.getContactNumber())
                .profilePicture(adminDto.getProfilePicture())
                .bloodGroup(adminDto.getBloodGroup())
                .designation(adminDto.getDesignation())
                .emergencyContactNumber(adminDto.getEmergencyContactNumber())
                .nidFrontPicture(adminDto.getNidFrontPicture())
                .nidBackPicture(adminDto.getNidBackPicture())
                .build();
        AdminEntity savedAdmin = adminRepository.save(admin);
        return ResponseEntity.ok(savedAdmin);
    }

    @Override
    public List<AdminEntity> getAllAdmins() {
        List<AdminEntity> admins = adminRepository.findAll();
        if (admins.isEmpty()) {
            throw new NoAdminsFoundException("No admins found.");
        }
        return admins;
    }

    @Override
    public ResponseEntity<Object> getAdminById(Long id) {
        Optional<AdminEntity> adminOptional = adminRepository.findById(id);
        if (adminOptional.isPresent()) {
            return ResponseEntity.ok(adminOptional.get());
        } else {
            throw new AdminNotFoundException("Admin not found with ID: " + id);
        }
    }

    @Override
    public ResponseEntity<Object> updateAdmin(Long id, AdminDto adminDto) {
        Optional<AdminEntity> adminOptional = adminRepository.findById(id);

        Optional<UserEntity> userOptional = userRepository.findById(id);
        if (adminOptional.isPresent()) {
            UserEntity user = userOptional.get();


            user.setName(adminDto.getName());
            user.setEmail(adminDto.getEmail());
            user.setContactNumber(adminDto.getContactNumber());
            user.setProfilePicture(adminDto.getProfilePicture());
            user.setBloodGroup(adminDto.getBloodGroup());
            user.setDesignation(adminDto.getDesignation());
            user.setEmergencyContactNumber(adminDto.getEmergencyContactNumber());
            user.setNidFrontPicture(adminDto.getNidFrontPicture());
            user.setNidBackPicture(adminDto.getNidBackPicture());

            UserEntity updatedUser = userRepository.save(user);


            AdminEntity admin = adminOptional.get();

            admin.setName(adminDto.getName());
            admin.setEmail(adminDto.getEmail());
            admin.setContactNumber(adminDto.getContactNumber());
            admin.setProfilePicture(adminDto.getProfilePicture());
            admin.setBloodGroup(adminDto.getBloodGroup());
            admin.setDesignation(adminDto.getDesignation());
            admin.setEmergencyContactNumber(adminDto.getEmergencyContactNumber());
            admin.setNidFrontPicture(adminDto.getNidFrontPicture());
            admin.setNidBackPicture(adminDto.getNidBackPicture());

            AdminEntity updatedAdmin = adminRepository.save(admin);

            return ResponseEntity.ok(updatedAdmin);
        } else {
            throw new AdminNotFoundException("Admin not found with ID: " + id);
        }
    }


    @Override
    public List<AdminEntity> searchByNameEmailPhoneNumber(String nameEmailPhone) {
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!userEntity.isActive()) {
            throw new UserNotActiveException("User not active");
        }

        List<AdminEntity> users = adminRepository.searchByNameEmailPhoneNumber(nameEmailPhone);
        if (users.isEmpty()) {
            throw new UserNotFoundException("Admin not found.");
        }
        return users;
    }


}
