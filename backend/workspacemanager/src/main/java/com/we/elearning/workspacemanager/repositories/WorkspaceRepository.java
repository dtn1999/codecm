package com.we.elearning.workspacemanager.repositories;

import com.we.elearning.workspacemanager.entities.Workspace;
import com.we.elearning.workspacemanager.entities.WorkspaceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
    Boolean  existsByPortAndStatus(int port, WorkspaceStatus status);
}
