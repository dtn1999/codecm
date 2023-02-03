package io.codercm.playground.providers;

import io.codercm.playground.playground.dtos.PlaygroundDto;
import io.codercm.playground.playground.dtos.PlaygroundStorageDto;
import io.codercm.playground.playground.entities.Playground;
import io.codercm.playground.providers.entities.CreateRunnerResponse;
import io.codercm.playground.providers.entities.CreateStorageResponse;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ResourceProvider {
    CreateStorageResponse createStorage(@NonNull String volumeId);
    CreateRunnerResponse createPlayground(@NonNull String volumeId, int codeServerPort, int playgroundAppPort);
    void startPlayground(@NonNull String runnerId);
    CreateRunnerResponse restorePlayground(@NonNull String volumeId, int codeServerPort, int playgroundAppPort);
    void deletePlayground(@NonNull String runnerId);
    List<PlaygroundDto> listPlaygrounds();
}
