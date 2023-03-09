package com.we.elearning.workspacemanager.services.providers.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.we.elearning.workspacemanager.entities.Workspace;
import com.we.elearning.workspacemanager.services.providers.CreateWorkspaceRequest;
import com.we.elearning.workspacemanager.services.providers.ResourceProvider;
import com.we.elearning.workspacemanager.services.providers.Runner;
import com.we.elearning.workspacemanager.utils.GitUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Optional;

@Service("dockerProvider")
@Slf4j
public class DockerProvider implements ResourceProvider {
    private final DockerClient dockerClient;
    private final String dockerImage;
    private final String dockerVolumesPath;
    private final String codeServerWorkspacePath;

    public DockerProvider(DockerClient dockerClient,
                          @Value("${playground.provider.docker.base-image}") String dockerImage,
                          @Value("${playground.provider.docker.volumes-path}") String dockerVolumesPath,
                          @Value("${coder-server.workspace-path}") String codeServerWorkspacePath) {
        this.dockerClient = dockerClient;
        this.dockerImage = dockerImage;
        this.dockerVolumesPath = dockerVolumesPath;
        this.codeServerWorkspacePath = codeServerWorkspacePath;
    }

    @Override
    public Runner createWorkspace(CreateWorkspaceRequest request) {
        String githubRepoUrl = request.getGithubRepoUrl();
        int codeServerPort = request.getCodeServerPort();
        String volumeName = request.getVolumeName();
        log.info("Creating workspace for github repo: {}, code server port: {}, volume name: {}",
                githubRepoUrl, codeServerPort, volumeName);
        //1. Provision volume
        String repositoryName = GitUtils.getRepositoryName(githubRepoUrl);
        String volumeMountPath = String.format("%s/%s/%s", dockerVolumesPath, volumeName, repositoryName);
        GitUtils.cloneRepository(githubRepoUrl, volumeMountPath);
        //2. Create a container for the workspace
        HostConfig hostConfig = HostConfig.newHostConfig()
                .withPortBindings(PortBinding.parse(String.format("%d:%d", codeServerPort, 8080)))
                .withBinds(Bind.parse(String.format("%s:%s", volumeMountPath, codeServerWorkspacePath)));
        CreateContainerResponse createContainerResponse = dockerClient.createContainerCmd(dockerImage)
                .withHostConfig(hostConfig)
                .exec();
        log.info("Container created successfully");
        //3. Start the container
        log.info("Starting container");
//        dockerClient.startContainerCmd(createContainerResponse.getId()).exec();
        Optional<Container> first = dockerClient.listContainersCmd().exec()
                .stream()
                .filter(container -> container.getId().equals(createContainerResponse.getId()))
                .findFirst();
        //2. Construct workspace object
        /*
        return Workspace.builder()
                .runnerId(createContainerResponse.getId())
                .host("localhost")
                .port(codeServerPort)
                .build();
         */
        return null;
    }

    @Override
    public void deleteWorkspace(Runner runner) {
        String runnerId = "";
        String volumeName = "";
        log.info("Deleting workspace with runner id: {}", runnerId);
        dockerClient.listContainersCmd()
                .exec()
                .stream()
                .filter(container -> container.getId().equals(runnerId))
                .findFirst()
                .ifPresent(container -> {
                    if(container.getState().equals("running")) {
                        //1. Kill the container
                        dockerClient.killContainerCmd(container.getId()).exec();
                    }
                    //2. Remove the container
                    dockerClient.removeContainerCmd(container.getId()).exec();
                    //3. Remove the volume
                    String volumeMountPath = String.format("%s/%s", dockerVolumesPath, volumeName);
                    FileUtils.deleteQuietly(new File(volumeMountPath));
                });
    }
}