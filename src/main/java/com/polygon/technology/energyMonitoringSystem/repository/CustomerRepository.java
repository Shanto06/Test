package com.polygon.technology.energyMonitoringSystem.repository;

import com.polygon.technology.energyMonitoringSystem.entity.CustomerEntity;
import com.polygon.technology.energyMonitoringSystem.entity.GroupEntity;
import com.polygon.technology.energyMonitoringSystem.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    CustomerEntity findByEmail(String email);

    List<CustomerEntity> findByGroup(GroupEntity group);

//    CustomerEntity findByEmail(String email);


    @Query(nativeQuery = true, value = "SELECT COUNT(id) FROM Energy_Monitoring_System.customers WHERE is_active = true AND group_id = :groupId")
    Long countCustomerByActiveStatus(Long groupId);
}
