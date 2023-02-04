package io.codercm.playground.providers.digitalocean;

import com.fasterxml.jackson.databind.JsonNode;
import io.codercm.playground.playground.dtos.PlaygroundDto;
import io.codercm.playground.providers.ResourceProvider;
import io.codercm.playground.providers.entities.CreateRunnerResponse;
import io.codercm.playground.providers.entities.CreateStorageResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service("digitalOceanResourceProvider")
public class DigitalOceanProvider implements ResourceProvider {
    private final WebClient webClient;

    public DigitalOceanProvider(@Qualifier("digitalOceanWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public CreateStorageResponse createStorage(@NonNull String volumeId) {
        Map<String,Object> requestBody = Map.of(
                "name", volumeId,
                "size_gigabytes", 5,
                "region", "fra1",
                "description", "Playground volume"
        );
        JsonNode responseBody = webClient.post()
                .uri("/v2/volumes")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();
        return CreateStorageResponse.builder()
                .size(responseBody.get("volume").get("size_gigabytes").asInt())
                .volumeId(responseBody.get("volume").get("id").asText())
                .volumeName(responseBody.get("volume").get("name").asText())
                .build();
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
