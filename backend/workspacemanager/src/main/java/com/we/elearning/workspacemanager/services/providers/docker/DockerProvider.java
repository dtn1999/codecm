package com.we.elearning.workspacemanager.services.providers.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.we.elearning.workspacemanager.services.providers.*;
import com.we.elearning.workspacemanager.utils.GitUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service("dockerProvider")
@Slf4j
public class DockerProvider implements ResourceProvider {
    private final DockerClient dockerClient;
    private final String dockerImage;
    private final String dockerVolumesPath;
    private final String codeServerWorkspacePath;
    private final String codeServerUserDataPath;

    public DockerProvider(DockerClient dockerClient,
                          @Value("${playground.provider.docker.base-image}") String dockerImage,
                          @Value("${playground.provider.docker.volumes-path}") String dockerVolumesPath,
                          @Value("${coder-server.workspace-path}") String codeServerWorkspacePath,
                          @Value("${coder-server.user-data-path}") String codeServerUserDataPath) {
        this.dockerClient = dockerClient;
        this.dockerImage = dockerImage;
        this.dockerVolumesPath = dockerVolumesPath;
        this.codeServerWorkspacePath = codeServerWorkspacePath;
        this.codeServerUserDataPath = codeServerUserDataPath;
    }

    @Override
    @SneakyThrows
    public Runner createWorkspace(CreateWorkspaceRequest request) {
        String githubRepoUrl = request.getGithubRepoUrl();
        int codeServerPort = request.getCodeServerPort();
        String volumeName = request.getVolumeName();
        log.info("Creating workspace for github repo: {}, code server port: {}, volume name: {}",
                githubRepoUrl, codeServerPort, volumeName);
        List<Bind> binds = setUpUserWorkspace(volumeName, githubRepoUrl);
        String workspacePath = String.format("%s/%s", dockerVolumesPath, volumeName);
        HostConfig hostConfig = HostConfig.newHostConfig()
                .withPortBindings(PortBinding.parse(String.format("%d:%d", codeServerPort, 8080)))
                .withBinds(binds);
        CreateContainerResponse createContainerResponse = dockerClient.createContainerCmd(dockerImage)
                .withHostConfig(hostConfig)
                .exec();
        log.info("Container created successfully");
        RunnerDetails runnerDetails = RunnerDetails.builder()
                .id(createContainerResponse.getId())
                .name(createContainerResponse.getId().substring(0, 12))
                .port(codeServerPort)
                .host("localhost")
                .volume(VolumeDetails.builder()
                        .name(volumeName)
                        .mountPath(workspacePath)
                        .build())
                .build();
        log.info("Runner with the following details will be created: {}", runnerDetails);
        return new DockerRunner(runnerDetails, dockerClient);
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
                    if (container.getState().equals("running")) {
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

    /**
     * This function set up the workspace for the user. Setting up the workspace includes:
     * 1. Cloning the user's repository into the workspace folder of the code-server
     * 2. Creating a user-data folder for saving the user's session data like extensions, settings, etc.
     * @param volumeName the name of the volume that will be created for the workspace
     * @param githubRepoUrl the url of the repository that will be cloned into the workspace
     * @return a list of binds that will be used to mount the workspace and user-data folders into the container
     */
    public List<Bind> setUpUserWorkspace(String volumeName, String githubRepoUrl) {
        String workspacePath = String.format("%s/%s", dockerVolumesPath, volumeName);
        String userDataPath = String.format("%s/%s", workspacePath, "user-data");
        String repositoryName = GitUtils.getRepositoryName(githubRepoUrl);
        String codePath = String.format("%s/%s/%s", dockerVolumesPath, volumeName, repositoryName);
        GitUtils.cloneRepository(githubRepoUrl, codePath);
        return List.of(
                Bind.parse(String.format("%s:%s", codePath, codeServerWorkspacePath)),
                Bind.parse(String.format("%s:%s", userDataPath, codeServerUserDataPath))
        );
    }
}