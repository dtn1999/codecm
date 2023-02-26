package com.we.elearning.workspacemanager.services;

import com.we.elearning.workspacemanager.entities.WorkspaceVolume;
import com.we.elearning.workspacemanager.repositories.WorkspaceVolumeRepository;
import com.we.elearning.workspacemanager.utils.HashUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WorkspaceVolumeService {
    private final WorkspaceVolumeRepository workspaceVolumeRepository;
    private final int volumeNameLength;

    public WorkspaceVolumeService(WorkspaceVolumeRepository workspaceVolumeRepository,
                                  @Value("${playground.provider.volume-name-length}") int volumeNameLength) {
        this.workspaceVolumeRepository = workspaceVolumeRepository;
        this.volumeNameLength = volumeNameLength;
    }

    public WorkspaceVolume createWorkspaceVolume(int size) {
        String name = HashUtils.getHash(String.valueOf(System.currentTimeMillis()), volumeNameLength);
        while (workspaceVolumeRepository.existsByName(name)) {
            name = HashUtils.getHash(String.valueOf(System.currentTimeMillis()), volumeNameLength);
        }
        return workspaceVolumeRepository.save(
                WorkspaceVolume.builder()
                        .name(name)
                        .size(size)
                        .build()
        );
    }
}
