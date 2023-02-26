package com.we.elearning.workspacemanager.repositories;

import com.we.elearning.workspacemanager.entities.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
    List<Workspace> findByPort(int port);
    Optional<Workspace> findByRunnerId(String runnerId);
}
