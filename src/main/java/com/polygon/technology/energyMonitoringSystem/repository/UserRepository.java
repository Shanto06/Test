package com.polygon.technology.energyMonitoringSystem.repository;

import com.polygon.technology.energyMonitoringSystem.dto.UserDto;
import com.polygon.technology.energyMonitoringSystem.entity.AdminEntity;
import com.polygon.technology.energyMonitoringSystem.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for interacting with the database for UserEntity entities.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    /**
     * Retrieves a user by their email address.
     *
     * @param email The email address of the user.
     * @return The UserEntity associated with the provided email address.
     */
    UserEntity findByEmail(String email);

//    List<UserDto> searchByNameEmailPhoneNumber(String nameEmailPhone);

//    @Query("SELECT NEW com.polygon.technology.energyMonitoringSystem.dto.UserDto(du.Id, du.name, du.email, du.contactNumber, du.isActive) FROM UserEntity du")
//    List<UserDto> findAllByCustomFeature();



}
