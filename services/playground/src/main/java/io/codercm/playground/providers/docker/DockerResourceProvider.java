package io.codercm.playground.providers.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.CreateVolumeResponse;
import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import io.codercm.playground.playground.dtos.PlaygroundDto;
import io.codercm.playground.playground.dtos.PlaygroundStorageDto;
import io.codercm.playground.playground.entities.PlaygroundStatus;
import io.codercm.playground.providers.ResourceProvider;
import io.codercm.playground.providers.entities.CreateRunnerResponse;
import io.codercm.playground.providers.entities.CreateStorageResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "dockerResourceProvider")
@RequiredArgsConstructor
public class DockerResourceProvider implements ResourceProvider {
    private final DockerClient dockerClient;
    @Value(value = "${playground.docker.base-image}")
    private String image;

    private PlaygroundDto toPlayground(Container container) {
        return PlaygroundDto.builder()
                .name(DockerUtils.getContainerName(container))
                .build();
    }

    @Override
    public CreateStorageResponse createStorage(@NonNull String volumeName) {
        CreateVolumeResponse response = dockerClient.createVolumeCmd()
                .withName(volumeName)
                .exec();
        return CreateStorageResponse.builder()
                .volumeId(response.getName())
                .volumeName(response.getName())
                .build();
    }

    @Override
    public CreateRunnerResponse createPlayground(@NonNull String volumeId, int codeServerPort, int playgroundAppPort) {
        HostConfig hostConfig = HostConfig.newHostConfig()
                .withPortBindings(PortBinding.parse(String.format("%d:%d", codeServerPort, 8080)),
                        PortBinding.parse(String.format("%d:%d", playgroundAppPort, 3000)))
                .withBinds(Bind.parse(String.format("%s:/config", volumeId)));

        List<String> env = List.of("PUID=1000", "PGID=1000", "TZ=Europe/London", "PASSWORD=", "HASHED_PASSWORD=", "SUDO_PASSWORD=", "SUDO_PASSWORD_HASH=", "DEFAULT_WORKSPACE=/config/workspace");

        CreateContainerResponse response = dockerClient.createContainerCmd(image)
                .withHostConfig(hostConfig)
                .withEnv(env)
                .exec();

        return CreateRunnerResponse.builder()
                .runnerId(response.getId())
                .host("localhost")
                .port(codeServerPort)
                .build();
    }

    @Override
    public void startPlayground(@NonNull String runnerId) {
        dockerClient.startContainerCmd(runnerId).exec();
    }


    @Override
    public CreateRunnerResponse restorePlayground(@NonNull String volumeId, int codeServerPort, int playgroundAppPort) {
        return null;
    }

    @Override
    public void deletePlayground(@NonNull String runnerId) {

    }

    @Override
    public List<PlaygroundDto> listPlaygrounds() {
        return null;
    }
}
