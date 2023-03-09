package com.we.elearning.workspacemanager.scheduling;

import com.github.dockerjava.api.model.Container;
import com.we.elearning.workspacemanager.repositories.WorkspaceRepository;
import com.we.elearning.workspacemanager.services.providers.ResourceProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkspaceCleanUpService {
    private final WorkspaceRepository workspaceRepository;
    private final ResourceProvider resourceProvider;
    public void cleanUp() {

    }
}
