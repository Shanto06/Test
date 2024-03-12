package com.polygon.technology.energyMonitoringSystem.service.Impl;

import com.polygon.technology.energyMonitoringSystem.utils.*;
import com.polygon.technology.energyMonitoringSystem.dto.*;
import com.polygon.technology.energyMonitoringSystem.entity.*;
import com.polygon.technology.energyMonitoringSystem.repository.*;
import com.polygon.technology.energyMonitoringSystem.service.*;
import com.polygon.technology.energyMonitoringSystem.exception.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    /**
     * Registers a new user.
     *
     * @param userDto The model containing user details.
     * @return Map containing authentication response and the saved user's ID.
     */
    @Override
    public Map<String, Object> register(UserDto userDto) {
        UserEntity userExistedAlready = userRepository.findByEmail(userDto.getEmail());
        if (userExistedAlready != null) {
            throw new UserAlreadyExistException("This Email Already Existed");
        }

        UserEntity userEntity = UserEntity.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .contactNumber(userDto.getContactNumber())
                .profilePicture(userDto.getProfilePicture())
                .bloodGroup(userDto.getBloodGroup())
                .designation(userDto.getDesignation())
                .emergencyContactNumber(userDto.getEmergencyContactNumber())
                .nidFrontPicture(userDto.getNidFrontPicture())
                .nidBackPicture(userDto.getNidBackPicture())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(getRole(userDto))
                .isActive(true)
                .build();
        UserEntity savedUser = userRepository.save(userEntity);
        AuthenticationResponse authRes = AuthenticationResponse.builder()
                .token(jwtService.generateToken(savedUser))
                .build();
        Map<String, Object> response = new HashMap<>();
        response.put("authRes", authRes);
        response.put("savedUserId", savedUser.getId());
        return response;
    }

    /**
     * Retrieves the role from the user request model.
     *
     * @param model The user request model.
     * @return Role enum value.
     */
    private Role getRole(UserDto model) {
        Role role;
        switch (model.getRole()) {
            case "ADMIN" -> role = Role.ADMIN;
            case "CUSTOMER" -> role = Role.CUSTOMER;
            default -> throw new IllegalArgumentException("Invalid role!");
        }
        return role;
    }

    /**
     * Logs in a user with the provided credentials.
     *
     * @param authenticationDto The authentication request containing email and password.
     * @return AuthenticationResponse containing the generated JWT token.
     */
    public AuthenticationResponse login(AuthenticationDto authenticationDto) {
        UserEntity user1 = userRepository.findByEmail(authenticationDto.getEmail());
        if(user1.isActive())
        {
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                authenticationDto.getEmail(),
                                authenticationDto.getPassword()
                        )
                );
            } catch (Exception e) {
                throw new EmailPasswordNotMatchException("Wrong Login Credentials");
            }

            var user = userRepository.findByEmail(authenticationDto.getEmail());
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } else {
            throw new UserNotActiveException("User not active");
        }
    }

    /**
     * Resets the password for a user with the given ID.
     *
     * @param userId            The ID of the user.
     * @param userDto  The model containing the new password.
     * @return ResponseEntity containing the updated UserEntity.
     */
    @Override
    public ResponseEntity<Object> reseatPassword(Long userId, UserDto userDto) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if(user.isEmpty())
        {
            throw new UserNotFoundException("Invalid user Id " + userId);
        }
        else {
            UserEntity user1 = user.get();
            user1.setPassword(passwordEncoder.encode(userDto.getPassword()));
            UserEntity saveUser = userRepository.save(user1);
            return ResponseEntity.ok(saveUser);
        }
    }

    /**
     * Changes the active status of a user (either staff or admin) with the given ID.
     *
     * @param userId The ID of the user (staff or admin).
     * @return ResponseEntity containing the status change message.
     */
    @Override
    public ResponseEntity<Object> statusChange(Long userId) {

        Optional<UserEntity> userOptional = userRepository.findById(userId);


        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();

            user.setActive(!user.isActive());

            userRepository.save(user);
            return ResponseEntity.ok("Staff with ID: " + userId + " has been changed.");
        } else {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }
    }

    @Override
    public String generateRandomPassword() {
            return RandomStringUtils.randomAlphanumeric(10);
    }

    /**
     * Retrieves a list of all users.
     *
     * @return List of UserEntity.
     */
    @Override
    public List<UserEntity> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users found.");
        }
        return users;
    }




}
