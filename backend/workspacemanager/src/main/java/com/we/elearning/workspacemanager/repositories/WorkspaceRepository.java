package com.we.elearning.workspacemanager.repositories;

import com.we.elearning.workspacemanager.entities.Workspace;
import com.we.elearning.workspacemanager.entities.WorkspaceStatus;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface WorkspaceRepository extends ReactiveCrudRepository<Workspace, Long> {
    Flux<Workspace> findByPort(int port);
    Mono<Boolean>  existsByPortAndStatus(int port, WorkspaceStatus status);
}
