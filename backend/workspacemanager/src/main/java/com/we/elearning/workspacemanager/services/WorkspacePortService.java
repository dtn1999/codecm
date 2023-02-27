package com.we.elearning.workspacemanager.services;

import com.we.elearning.workspacemanager.entities.WorkspaceStatus;
import com.we.elearning.workspacemanager.repositories.WorkspaceRepository;
import com.we.elearning.workspacemanager.utils.RandomUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkspacePortService {
    private final int MIN_PORT = 9000;
    private final int MAX_PORT = 9999;
    private final WorkspaceRepository workspaceRepository;

    public int getAvailableWorkspacePort() {
        int port = RandomUtils.getNumber(MIN_PORT, MAX_PORT);
        while(workspaceRepository.findByPort(port)
                .stream()
                .anyMatch(workspace -> workspace.getStatus().equals(WorkspaceStatus.RUNNING))) {
            port = RandomUtils.getNumber(MIN_PORT, MAX_PORT);
        }
        return port;
    }
}