package io.codercm.playground.providers;

import io.codercm.playground.playground.dtos.PlaygroundDto;
import io.codercm.playground.playground.entities.Playground;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ResourceProvider {
    Optional<PlaygroundDto> createPlayground(PlaygroundDto playground);
    Optional<PlaygroundDto> backupPlayground(UUID playgroundId);
    Optional<PlaygroundDto> restorePlayground(UUID playgroundId);
    void deletePlayground(UUID playgroundId);
    List<PlaygroundDto> listPlaygrounds();
}
