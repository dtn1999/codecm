package com.we.elearning.workspacemanager.services.providers.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.exception.NotFoundException;
import com.github.dockerjava.api.model.Container;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DockerProviderTest {

    @Autowired
    DockerClient dockerClient;

    @Test
    void createWorkspace() {
        CreateContainerResponse exec = dockerClient.createContainerCmd("hello-world").exec();
        Container containerNotFound = dockerClient.listContainersCmd().withShowAll(true)
                .withIdFilter(List.of(exec.getId()))
                .exec()
                .stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Container not found"));

        System.out.println(containerNotFound);
    }

    @Test
    void deleteWorkspace() {
    }
}