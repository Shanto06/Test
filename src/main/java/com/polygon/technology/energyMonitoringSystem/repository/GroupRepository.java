package com.polygon.technology.energyMonitoringSystem.repository;

import com.polygon.technology.energyMonitoringSystem.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
    GroupEntity findByGroupName(String groupName);
}
