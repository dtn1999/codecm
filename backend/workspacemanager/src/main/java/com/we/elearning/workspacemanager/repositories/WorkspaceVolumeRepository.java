package com.we.elearning.workspacemanager.repositories;

import com.we.elearning.workspacemanager.entities.WorkspaceVolume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkspaceVolumeRepository extends JpaRepository<WorkspaceVolume, Long> {
     boolean existsByName(String name);
}
