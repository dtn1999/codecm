package io.codercm.playground.providers.linode;

import io.codercm.playground.playground.dtos.PlaygroundDto;
import io.codercm.playground.providers.ResourceProvider;
import io.codercm.playground.providers.entities.CreateRunnerResponse;
import io.codercm.playground.providers.entities.CreateStorageResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("linodeResourceProvider")
public class LinodeResourceProvider implements ResourceProvider {
    @Value("${playground.linode.access-token}")
    private String linodeToken;
    @Override
    public CreateStorageResponse createStorage(@NonNull String volumeId) {
        return null;
    }

    @Override
    public CreateRunnerResponse createPlayground(@NonNull String volumeId, int codeServerPort, int playgroundAppPort) {
        return null;
    }

    @Override
    public void startPlayground(@NonNull String runnerId) {

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
