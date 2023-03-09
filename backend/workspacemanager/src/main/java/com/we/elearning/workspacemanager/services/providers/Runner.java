package com.we.elearning.workspacemanager.services.providers;

public interface Runner {
    RunnerDetails getDetails();
    void start();
    void stop();
    void restart();
    void clean();
}
