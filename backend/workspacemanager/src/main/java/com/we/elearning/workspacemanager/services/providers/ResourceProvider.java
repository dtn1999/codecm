package com.we.elearning.workspacemanager.services.providers;

import com.we.elearning.workspacemanager.entities.Workspace;

/**
 *
 */
public interface ResourceProvider {
    Workspace createWorkspace(int codeServerPort, String volumeName, String githubRepoUrl);
    void deleteWorkspace(String runnerId);
}
