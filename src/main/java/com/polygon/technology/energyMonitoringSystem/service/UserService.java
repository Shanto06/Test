package com.polygon.technology.energyMonitoringSystem.service;

import com.polygon.technology.energyMonitoringSystem.dto.*;
import com.polygon.technology.energyMonitoringSystem.entity.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

/**
 * Service interface for managing user-related operations.
 */
public interface UserService {

    /**
     * Registers a new user.
     *
     * @param requestModel The request model containing user details.
     * @return Map containing the authentication response and the ID of the saved user.
     */
    Map<String, Object> register(UserDto requestModel);

    /**
     * Performs user authentication and generates an authentication token.
     *
     * @param authenticationDto The request model containing user credentials.
     * @return AuthenticationResponse containing the generated token.
     */
    AuthenticationResponse login(AuthenticationDto authenticationDto);
    /**
     * Resets the password for a user.
     *
     * @param userId              The ID of the user for password reset.
     * @param userRequestModel    The request model containing the new password.
     * @return ResponseEntity containing the updated UserEntity.
     */
    ResponseEntity<Object> reseatPassword(Long userId, UserDto userRequestModel);

    /**
     * Changes the active status of a user.
     *
     * @param userId The ID of the user to change the status.
     * @return ResponseEntity with a confirmation message.
     */
    ResponseEntity<Object> statusChange(Long userId);

    String generateRandomPassword();
    List<UserEntity> getAllUsers();


//    List<UserDto> searchByNameEmailPhoneNumber(String nameEmailPhone);
}
