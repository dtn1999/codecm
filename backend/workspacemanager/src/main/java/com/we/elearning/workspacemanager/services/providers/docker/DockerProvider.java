package com.we.elearning.workspacemanager.services.providers.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.we.elearning.workspacemanager.entities.Workspace;
import com.we.elearning.workspacemanager.services.providers.ResourceProvider;
import com.we.elearning.workspacemanager.utils.GitUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dockerProvider")
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
    public Workspace createWorkspace(int codeServerPort, String volumeName, String githubRepoUrl) {
        //1. Provision volume
        String repositoryName = GitUtils.getRepositoryName(githubRepoUrl);
        String volumeMountPath= String.format("%s/%s/%s", dockerVolumesPath,volumeName, repositoryName);
        GitUtils.cloneRepository(githubRepoUrl,volumeMountPath);
        //2. Create a container for the workspace
        HostConfig hostConfig = HostConfig.newHostConfig()
                .withPortBindings(PortBinding.parse(String.format("%d:%d", codeServerPort, 8080)))
                .withBinds(Bind.parse(String.format("%s:%s",volumeMountPath, codeServerWorkspacePath)));
        CreateContainerResponse createContainerResponse = dockerClient.createContainerCmd(dockerImage)
                .withHostConfig(hostConfig)
                .exec();
        dockerClient.startContainerCmd(createContainerResponse.getId()).exec();
        //2. Construct workspace object
        return Workspace.builder()
                .runnerId(createContainerResponse.getId())
                .host("localhost")
                .port(codeServerPort)
                .build();
    }

    @Override
    public void deleteWorkspace(String runnerId) {
        dockerClient.killContainerCmd(runnerId).exec();
        dockerClient.removeContainerCmd(runnerId).exec();
    }
}