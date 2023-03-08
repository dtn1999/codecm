package com.we.elearning.workspacemanager.services;

import com.we.elearning.workspacemanager.entities.WorkspaceVolume;
import com.we.elearning.workspacemanager.repositories.WorkspaceVolumeRepository;
import com.we.elearning.workspacemanager.utils.HashUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicBoolean;

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
        AtomicBoolean isNameInUse = new AtomicBoolean(true);
        return Mono.fromCallable(() -> HashUtils.getHash(String.valueOf(System.currentTimeMillis()), volumeNameLength))
                .repeat()
                .map(name -> {
                    isNameInUse.set(workspaceVolumeRepository.existsByName(name));
                    return name;
                })
                .takeUntil(name -> !isNameInUse.get())
                .last()
                .map(name -> workspaceVolumeRepository.save(
                        WorkspaceVolume.builder()
                                .name(name)
                                .size(size)
                                .build()
                ));
    }
}