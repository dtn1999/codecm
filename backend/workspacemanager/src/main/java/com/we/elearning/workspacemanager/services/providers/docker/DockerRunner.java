package com.we.elearning.workspacemanager.services.providers.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.exception.NotFoundException;
import com.github.dockerjava.api.exception.NotModifiedException;
import com.we.elearning.workspacemanager.services.providers.Runner;
import com.we.elearning.workspacemanager.services.providers.RunnerDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;

@Slf4j
public class DockerRunner implements Runner {
    private final DockerClient dockerClient;
    private final RunnerDetails details;

    public DockerRunner(RunnerDetails details, DockerClient dockerClient) {
        this.dockerClient = dockerClient;
        this.details = details;
        // Check if the container exists
        dockerClient.listContainersCmd().withShowAll(true)
                .withIdFilter(List.of(details.getId()))
                .exec()
                .stream()
                .findAny()
                .orElseThrow(() -> new NotFoundException("Container not found"));
    }

    @Override
    public RunnerDetails getDetails() {
        return details;
    }

    @Override
    public void start() {
        try {
            log.info("Starting container with id: {} and name: {}", details.getId(), details.getName());
            dockerClient.startContainerCmd(details.getId()).exec();
            log.info("Container with id: {} and name: {} started", details.getId(), details.getName());
        } catch (NotModifiedException e) {
            log.warn("Container with id: {} and name: {} already started", details.getId(), details.getName());
        }
    }

    @Override
    public void stop() {
        try {
            log.info("Stopping container with id: {} and name: {}", details.getId(), details.getName());
            dockerClient.stopContainerCmd(details.getId()).exec();
            log.info("Container with id: {} and name: {} stopped", details.getId(), details.getName());
        } catch (NotModifiedException e) {
            log.warn("Container with id: {} and name: {} already stopped", details.getId(), details.getName());
        }
    }

    @Override
    public void restart() {
        log.info("Restarting container with id: {} and name: {}", details.getId(), details.getName());
        dockerClient.restartContainerCmd(details.getId()).exec();
        log.info("Container with id: {} and name: {} restarted", details.getId(), details.getName());
    }

    @Override
    public void clean() {
        log.info("Cleaning container with id: {} and name: {}", details.getId(), details.getName());
        dockerClient.listContainersCmd().withShowAll(true)
                .withIdFilter(List.of(details.getId()))
                .exec()
                .stream()
                .findAny()
                .ifPresent(container -> {
                    if (container.getState().equals("running")) {
                        log.info("Container with id: {} and name: {} is running, stopping it", details.getId(), details.getName());
                        stop();
                    }
                    log.info("Killing and removing container with id: {} and name: {}", details.getId(), details.getName());
                    dockerClient.killContainerCmd(details.getId()).exec();
                    dockerClient.removeContainerCmd(details.getId()).exec();
                    // delete volumes and workspace files
                    // TODO: Eventually add this
                    // dockerClient.listVolumesCmd().withFilter();
                    File workspace = new File(details.getVolume().getMountPath());
                    log.info("Deleting workspace directory: {}", workspace.getAbsolutePath());
                    FileUtils.deleteQuietly(workspace);
                });
    }
}