package com.we.elearning.workspacemanager.repositories;

import com.we.elearning.workspacemanager.entities.WorkspaceVolume;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface WorkspaceVolumeRepository extends ReactiveCrudRepository<WorkspaceVolume, Long> {
     Mono<Boolean> existsByName(String name);
}
