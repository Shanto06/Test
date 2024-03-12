package com.polygon.technology.energyMonitoringSystem.controllers;

import com.polygon.technology.energyMonitoringSystem.dto.*;
import com.polygon.technology.energyMonitoringSystem.entity.*;
import com.polygon.technology.energyMonitoringSystem.service.*;
import com.polygon.technology.energyMonitoringSystem.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<Object> createCustomer(@RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(customerService.createCustomer(customerDto));
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationDto authenticationDto) {
        return customerService.login(authenticationDto);
    }

    @GetMapping("/all")
    public List<CustomerEntity> getAllCustomers()
    {
        return customerService.getAllCustomers();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/count/{groupId}")
    public Long activeCustomerCount(@PathVariable Long groupId)
    {
        return customerService.activeCustomerCount(groupId);
    }

    @PostMapping("/status/{customerId}")
    public ResponseEntity<Object> statusChange(@PathVariable Long customerId) {
        return customerService.statusChange(customerId);
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<Object> updateGroup(@PathVariable Long customerId, @RequestBody CustomerDto customerDto) {
        return customerService.updateCustomer(customerId, customerDto);
    }
}
