package com.polygon.technology.energyMonitoringSystem.controllers;

import com.polygon.technology.energyMonitoringSystem.dto.AdminDto;
import com.polygon.technology.energyMonitoringSystem.dto.GroupDto;
import com.polygon.technology.energyMonitoringSystem.entity.GroupEntity;
import com.polygon.technology.energyMonitoringSystem.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @PostMapping("/create")
    public ResponseEntity<Object> createGroup(@RequestBody GroupDto groupDto) {
        return groupService.createGroup(groupDto);
    }
    @GetMapping("all")
    public List<GroupEntity> getAllGroups()
    {
        return groupService.getAllGroups();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getGroupById(@PathVariable Long id) {
        return groupService.getGroupById(id);
    }

    @PostMapping("/status/{groupId}")
    public ResponseEntity<Object> statusChange(@PathVariable Long groupId) {
        return groupService.statusChange(groupId);
    }

    @PutMapping("/update/{groupId}")
    public ResponseEntity<Object> updateGroup(@PathVariable Long groupId, @RequestBody GroupDto groupDto) {
        return groupService.updateGroup(groupId, groupDto);
    }
}
