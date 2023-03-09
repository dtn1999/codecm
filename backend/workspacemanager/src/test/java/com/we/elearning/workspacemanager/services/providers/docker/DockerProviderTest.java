package com.we.elearning.workspacemanager.services.providers.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InspectVolumeResponse;
import com.github.dockerjava.api.exception.NotFoundException;
import com.github.dockerjava.api.model.Container;
import com.we.elearning.workspacemanager.entities.WorkspaceVolume;
import com.we.elearning.workspacemanager.repositories.WorkspaceVolumeRepository;
import com.we.elearning.workspacemanager.services.providers.CreateWorkspaceRequest;
import com.we.elearning.workspacemanager.services.providers.Runner;
import com.we.elearning.workspacemanager.utils.GitUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DockerProviderTest {

    @Autowired
    DockerProvider dockerProvider;
    @Autowired
    WorkspaceVolumeRepository workspaceVolumeRepository;

    @Test
    void createWorkspace() {
        Runner runner = dockerProvider.createWorkspace(CreateWorkspaceRequest.builder()
                .volumeName("test")
                .codeServerPort(9888)
                .githubRepoUrl("https://github.com/dtn1999/pomodoro.git")
                .build());
        assertNotNull(runner);
        runner.start();
    }

    @Test
    void deleteWorkspace() {
        int count = workspaceVolumeRepository.countAllBy();
        System.out.println(count);
        workspaceVolumeRepository.save(WorkspaceVolume.builder()
                .name("test")
                        .size(0)
                .build());
        count = workspaceVolumeRepository.countAllBy();
        System.out.println(count);
    }
}