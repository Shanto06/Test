package com.polygon.technology.energyMonitoringSystem.service;

import com.polygon.technology.energyMonitoringSystem.dto.GroupDto;
import com.polygon.technology.energyMonitoringSystem.entity.GroupEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GroupService {
    ResponseEntity<Object> createGroup(GroupDto groupDto);

    List<GroupEntity> getAllGroups();

    ResponseEntity<Object> statusChange(Long groupId);

    ResponseEntity<Object> updateGroup(Long groupId, GroupDto groupDto);

    ResponseEntity<Object> getGroupById(Long id);
}
