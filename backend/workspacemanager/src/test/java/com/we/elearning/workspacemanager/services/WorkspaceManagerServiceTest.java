package com.we.elearning.workspacemanager.services;

import com.we.elearning.workspacemanager.common.dtos.ApiResponse;
import com.we.elearning.workspacemanager.entities.Workspace;
import com.we.elearning.workspacemanager.entities.WorkspaceVolume;
import com.we.elearning.workspacemanager.repositories.WorkspaceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WorkspaceManagerServiceTest {

    @Autowired
    WorkspaceManagerService workspaceManagerService;
    @Autowired
    WorkspaceRepository workspaceRepository;

    @Test
    void createWorkspace() {
        String githubRepoUrl = "https://github.com/dtn1999/pomodoro.git";
        Optional<ApiResponse> apiResponse = workspaceManagerService.createWorkspace(githubRepoUrl).blockOptional();
        System.out.println(apiResponse);
    }

    @Test
    void deleteWorkspace() {
        Workspace workspace = Workspace.builder()
                .host("host")
                .port(8080)
                .runnerId("runnerId")
                .workspaceVolume(WorkspaceVolume.builder()
                        .name("name")
                        .size(100)
                        .mountPath("/home")
                        .build())
                .build();
        workspaceRepository.save(workspace);
    }

    @Test
    void getAllWorkspaces() {
    }
}