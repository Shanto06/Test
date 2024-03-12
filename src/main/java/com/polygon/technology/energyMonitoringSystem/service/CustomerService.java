package com.polygon.technology.energyMonitoringSystem.service;

import com.polygon.technology.energyMonitoringSystem.dto.AuthenticationDto;
import com.polygon.technology.energyMonitoringSystem.dto.AuthenticationResponse;
import com.polygon.technology.energyMonitoringSystem.dto.CustomerDto;
import com.polygon.technology.energyMonitoringSystem.entity.CustomerEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface CustomerService {
    Map<String, Object> createCustomer(CustomerDto customerDto);

    List<CustomerEntity> getAllCustomers();

    Long activeCustomerCount(Long groupId);

    ResponseEntity<Object> statusChange(Long customerId);

    ResponseEntity<Object> getCustomerById(Long id);

    ResponseEntity<Object> updateCustomer(Long customerId, CustomerDto customerDto);

    AuthenticationResponse login(AuthenticationDto authenticationDto);
}
