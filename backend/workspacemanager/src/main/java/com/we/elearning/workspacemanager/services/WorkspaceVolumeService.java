package com.we.elearning.workspacemanager.services;

import com.we.elearning.workspacemanager.entities.WorkspaceVolume;
import com.we.elearning.workspacemanager.repositories.WorkspaceVolumeRepository;
import com.we.elearning.workspacemanager.utils.HashUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WorkspaceVolumeService {
    private final WorkspaceVolumeRepository workspaceVolumeRepository;
    private final int volumeNameLength;

    public WorkspaceVolumeService(WorkspaceVolumeRepository workspaceVolumeRepository,
                                  @Value("${playground.provider.volume-name-length}") int volumeNameLength) {
        this.workspaceVolumeRepository = workspaceVolumeRepository;
        this.volumeNameLength = volumeNameLength;
    }

    public Mono<WorkspaceVolume> createWorkspaceVolume(int size) {
        return Flux.generate(() -> HashUtils.getHash(String.valueOf(System.currentTimeMillis()), volumeNameLength),
                        (state, sink) -> {
                    Boolean isNameInUse = workspaceVolumeRepository.existsByName(state).block();
                    return Boolean.TRUE.equals(isNameInUse) ?
                            HashUtils.getHash(String.valueOf(System.currentTimeMillis()), volumeNameLength) : state;
                })
                .cast(String.class)
                .last()
                .flatMap(name -> workspaceVolumeRepository.save(
                        WorkspaceVolume.builder()
                                .name(name)
                                .size(size)
                                .build()
                ));
    }
}
