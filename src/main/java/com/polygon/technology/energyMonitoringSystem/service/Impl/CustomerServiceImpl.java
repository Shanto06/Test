package com.polygon.technology.energyMonitoringSystem.service.Impl;

import com.polygon.technology.energyMonitoringSystem.dto.*;
import com.polygon.technology.energyMonitoringSystem.entity.*;
import com.polygon.technology.energyMonitoringSystem.exception.*;
import com.polygon.technology.energyMonitoringSystem.repository.*;
import com.polygon.technology.energyMonitoringSystem.service.*;
import com.polygon.technology.energyMonitoringSystem.utils.CustomerJwtService;
import com.polygon.technology.energyMonitoringSystem.utils.JwtService;
import com.polygon.technology.energyMonitoringSystem.utils.Role;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final CustomerJwtService customerJwtService;

    @Override
    public Map<String, Object> createCustomer(CustomerDto customerDto) {
        CustomerEntity customer = customerRepository.findByEmail(customerDto.getEmail());
        Optional<GroupEntity> groupOptional = groupRepository.findById(customerDto.getGroupId());

        if(groupOptional.isEmpty())
        {
            throw new NoGroupFoundException("Group id is not valid");
        }

        GroupEntity group = groupOptional.get();

        if (group.getIsActive()) {
            if (customer != null) {
                throw new UserAlreadyExistException("Customer already existed");
            }
            GroupEntity group1 = groupOptional.get();
            CustomerEntity customer1 = CustomerEntity.builder()
                    .group(group1)
                    .name(customerDto.getName())
                    .email(customerDto.getEmail())
                    .contactNumber(customerDto.getContactNumber())
                    .isActive(true)
                    .profilePicture(customerDto.getProfilePicture())
                    .password(passwordEncoder.encode(customerDto.getPassword()))
                    .role(getRole(customerDto))
                    .build();

            CustomerEntity savedCustomer = customerRepository.save(customer1);

            AuthenticationResponse authRes = AuthenticationResponse.builder()
                    .token(customerJwtService.generateToken(savedCustomer))
                    .build();
            Map<String, Object> response = new HashMap<>();
            response.put("authRes", authRes);
            response.put("savedCustomerId", savedCustomer.getId());
            return response;
                
        } else {
            throw new NoGroupFoundException("Group is not active");
            }
    }


    private Role getRole(CustomerDto model) {
        Role role;
        switch (model.getRole()) {
            case "ADMIN" -> role = Role.ADMIN;
            case "CUSTOMER" -> role = Role.CUSTOMER;
            default -> throw new IllegalArgumentException("Invalid role!");
        }
        return role;
    }


    @Override
    public AuthenticationResponse login(AuthenticationDto authenticationDto) {
        CustomerEntity customer = customerRepository.findByEmail(authenticationDto.getEmail());
        if(customer.getIsActive())
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

            var customer1 = customerRepository.findByEmail(authenticationDto.getEmail());
            var jwtToken = customerJwtService.generateToken(customer1);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } else {
            throw new UserNotActiveException("User not active");
        }
    }


    @Override
    public List<CustomerEntity> getAllCustomers() {
        List<CustomerEntity> customer = customerRepository.findAll();
        if (customer.isEmpty()) {
            throw new NoCustomerFoundException("There is no customers");
        }
        return customer;
    }

    @Override
    public Long activeCustomerCount(Long groupId) {
        Optional<GroupEntity> groups = groupRepository.findById(groupId);
        if (groups.isEmpty()) {
            throw new InvalidGroupIdException("Group id is not valid");
        }
        return customerRepository.countCustomerByActiveStatus(groupId);
    }

    @Override
    public ResponseEntity<Object> statusChange(Long customerId) {
        Optional<CustomerEntity> customerOptional = customerRepository.findById(customerId);


        if (customerOptional.isPresent()) {
            CustomerEntity customer = customerOptional.get();
            customer.setIsActive(!customer.getIsActive());

            customerRepository.save(customer);
            return ResponseEntity.ok("customer with ID: " + customerId + " has been changed.");
        } else {
            throw new UserNotFoundException("customer not found with ID: " + customerId);
        }
    }

    @Override
    public ResponseEntity<Object> getCustomerById(Long id) {
        Optional<CustomerEntity> customerOptional = customerRepository.findById(id);
        if (customerOptional.isPresent()) {
            return ResponseEntity.ok(customerOptional.get());
        } else {
            throw new NoCustomerFoundException("Customer not found with ID: " + id);
        }
    }

    @Override
    public ResponseEntity<Object> updateCustomer(Long customerId, CustomerDto customerDto) {
        Optional<CustomerEntity> customerOptional = customerRepository.findById(customerId);
        Optional<GroupEntity> groupOptional = groupRepository.findById(customerDto.getGroupId());
        Optional<UserEntity> userOptional = userRepository.findById(customerId);

        if (groupOptional.isPresent()) {
            if (customerOptional.isPresent()) {
                CustomerEntity customer = customerOptional.get();
                GroupEntity group = groupOptional.get();

                customer.setName(customerDto.getName());
                customer.setGroup(group);
                customer.setEmail(customerDto.getEmail());
                customer.setContactNumber(customerDto.getContactNumber());
                customer.setProfilePicture(customerDto.getProfilePicture());

                CustomerEntity updatedCustomer = customerRepository.save(customer);

                return ResponseEntity.ok(updatedCustomer);
            } else {
                throw new NoCustomerFoundException("Customer not found with ID: " + customerId);
            }
        } else {
            throw new NoGroupFoundException("Group not found with ID: " + customerDto.getGroupId());
        }
    }



}
