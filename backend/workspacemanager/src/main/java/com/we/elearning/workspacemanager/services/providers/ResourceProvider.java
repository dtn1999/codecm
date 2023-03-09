package com.we.elearning.workspacemanager.services.providers;

/**
 *
 */
public interface ResourceProvider {
    Runner createWorkspace(CreateWorkspaceRequest request);
    Runner deleteWorkspace(RunnerDetails runnerDetails);
    Runner getRunner(RunnerDetails runnerDetails);
}
