package com.polygon.technology.energyMonitoringSystem.controllers;

import com.polygon.technology.energyMonitoringSystem.dto.*;
import com.polygon.technology.energyMonitoringSystem.entity.UserEntity;
import com.polygon.technology.energyMonitoringSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller handling operations related to user management.
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    /**
     * Endpoint for user registration.
     *
     * @param userDto UserRequestModel containing registration details.
     * @return ResponseEntity containing the result of the registration operation.
     */
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.register(userDto));
    }

    /**
     * Endpoint for user login.
     *
     * @param authenticationDto AuthenticationRequest containing login credentials.
     * @return AuthenticationResponse containing authentication result.
     */
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationDto authenticationDto) {
        return userService.login(authenticationDto);
    }


    /**
     * Endpoint to reset user password.
     *
     * @param userId         ID of the user whose password needs to be reset.
     * @param userDto UserRequestModel containing the new password.
     * @return ResponseEntity containing the result of the password reset operation.
     */
    @PostMapping("/resetPassword/{userId}")
    public ResponseEntity<Object> reseatPassword(@PathVariable Long userId, @RequestBody UserDto userDto) {
        return userService.reseatPassword(userId, userDto);
    }

    /**
     * Endpoint to change user status.
     *
     * @param userId ID of the user whose status needs to be changed.
     * @return ResponseEntity containing the result of the status change operation.
     */
    @PostMapping("/status/{userId}")
    public ResponseEntity<Object> statusChange(@PathVariable Long userId) {
        return userService.statusChange(userId);
    }

    @GetMapping("/randomPassword")
    public String generateRandomPassword()
    {
        return userService.generateRandomPassword();
    }


    @GetMapping("/all")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }





}
