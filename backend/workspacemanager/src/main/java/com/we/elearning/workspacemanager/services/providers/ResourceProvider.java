package com.we.elearning.workspacemanager.services.providers;

/**
 *
 */
public interface ResourceProvider {
    Runner createWorkspace(CreateWorkspaceRequest request);
    void deleteWorkspace(Runner runner);
}
