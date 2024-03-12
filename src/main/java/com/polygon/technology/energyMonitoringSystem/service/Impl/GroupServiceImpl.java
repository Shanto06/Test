package com.polygon.technology.energyMonitoringSystem.service.Impl;

import com.polygon.technology.energyMonitoringSystem.dto.*;
import com.polygon.technology.energyMonitoringSystem.entity.*;
import com.polygon.technology.energyMonitoringSystem.exception.*;
import com.polygon.technology.energyMonitoringSystem.repository.*;
import com.polygon.technology.energyMonitoringSystem.service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final CustomerRepository customerRepository;
    @Override
    public ResponseEntity<Object> createGroup(GroupDto groupDto) {
        GroupEntity group = groupRepository.findByGroupName(groupDto.getGroupName());
       if(Objects.nonNull(group))
       {
           throw new GroupNameAlreadyExistException("This Group Name Already Existed");
       }

       GroupEntity group1 = GroupEntity.builder()
               .groupName(groupDto.getGroupName())
               .groupIcon(groupDto.getGroupIcon())
               .isActive(true)
               .build();

        GroupEntity savedGroup = groupRepository.save(group1);
        return ResponseEntity.ok(savedGroup);
    }

    @Override
    public List<GroupEntity> getAllGroups() {
        List<GroupEntity> groups = groupRepository.findAll();
        if (groups.isEmpty()) {
            throw new NoGroupFoundException("There are no groups");
        }
        return groups;
    }

    @Override
    public ResponseEntity<Object> getGroupById(Long id) {
        Optional<GroupEntity> groupOptional = groupRepository.findById(id);
        if (groupOptional.isPresent()) {
            return ResponseEntity.ok(groupOptional.get());
        } else {
            throw new NoGroupFoundException("Group not found with ID: " + id);
        }
    }

    @Override
    public ResponseEntity<Object> statusChange(Long groupId) {
        Optional<GroupEntity> groupOptional = groupRepository.findById(groupId);


        if (groupOptional.isPresent()) {
            GroupEntity group = groupOptional.get();
            group.setIsActive(!group.getIsActive());

            groupRepository.save(group);
            return ResponseEntity.ok("group with ID: " + groupId + " has been changed.");
        } else {
            throw new UserNotFoundException("group not found with ID: " + groupId);
        }
    }

    @Override
    public ResponseEntity<Object> updateGroup(Long groupId, GroupDto groupDto) {
        Optional<GroupEntity> groupOptional = groupRepository.findById(groupId);
        if (groupOptional.isPresent()) {
            GroupEntity group = groupOptional.get();

            group.setGroupName(groupDto.getGroupName());
            group.setGroupIcon(groupDto.getGroupIcon());

            GroupEntity updatedGroup = groupRepository.save(group);

            return ResponseEntity.ok(updatedGroup);
        } else {
            throw new NoGroupFoundException("Group not found with ID: " + groupId);
        }
    }

}
