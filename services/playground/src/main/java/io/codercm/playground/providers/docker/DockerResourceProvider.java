package io.codercm.playground.providers.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import io.codercm.playground.playground.dtos.PlaygroundDto;
import io.codercm.playground.playground.entities.Playground;
import io.codercm.playground.providers.ResourceProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service(value = "dockerResourceProvider")
@RequiredArgsConstructor
public class DockerResourceProvider implements ResourceProvider {
    private final DockerClient dockerClient;
    @Value(value = "${playground.docker.base-image}")
    private String image;

    @Override
    public Optional<PlaygroundDto> createPlayground(PlaygroundDto playground) {
        return Optional.empty();
    }

    @Override
    public Optional<PlaygroundDto> backupPlayground(UUID playgroundId) {
        return Optional.empty();
    }

    @Override
    public Optional<PlaygroundDto> restorePlayground(UUID playgroundId) {
        return Optional.empty();
    }

    @Override
    public void deletePlayground(UUID playgroundId) {

    }

    @Override
    public List<PlaygroundDto> listPlaygrounds() {
        return dockerClient.listContainersCmd()
                .withShowAll(true)
                .withShowSize(true)
                .exec()
                .stream()
                .filter(container -> container.getImage().equals(image))
                .map(this::toPlayground)
                .toList();
    }

    private PlaygroundDto toPlayground(Container container) {
        return null;
    }
}
