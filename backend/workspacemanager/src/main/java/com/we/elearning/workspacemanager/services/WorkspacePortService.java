package com.we.elearning.workspacemanager.services;

import com.we.elearning.workspacemanager.entities.WorkspaceStatus;
import com.we.elearning.workspacemanager.repositories.WorkspaceRepository;
import com.we.elearning.workspacemanager.utils.RandomUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class WorkspacePortService {
    private final int MIN_PORT = 9000;
    private final int MAX_PORT = 9999;
    private final WorkspaceRepository workspaceRepository;

    public Mono<Integer> getAvailableWorkspacePort() {
        return Flux.generate(() -> RandomUtils.getNumber(MIN_PORT, MAX_PORT), (state, sink) -> {
                    Boolean isPortInUse = workspaceRepository
                            .existsByPortAndStatus(state, WorkspaceStatus.RUNNING)
                            .block();
                    return Boolean.TRUE.equals(isPortInUse) ? RandomUtils.getNumber(MIN_PORT, MAX_PORT) : state;
                })
                .cast(Integer.class)
                .last();
    }
}