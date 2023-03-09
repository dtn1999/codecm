package com.we.elearning.workspacemanager.repositories;

import com.we.elearning.workspacemanager.entities.WorkspaceVolume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceVolumeRepository extends JpaRepository<WorkspaceVolume, Long> {
    Boolean existsByName(String name);
    int countAllBy();
}
