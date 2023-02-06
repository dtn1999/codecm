package io.codercm.playground.providers.digitalocean;

import com.fasterxml.jackson.databind.JsonNode;
import io.codercm.playground.playground.dtos.PlaygroundDto;
import io.codercm.playground.providers.ResourceProvider;
import io.codercm.playground.providers.entities.CreateRunnerResponse;
import io.codercm.playground.providers.entities.CreateStorageResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("digitalOceanResourceProvider")
public class DigitalOceanProvider implements ResourceProvider {
    private final WebClient webClient;
    @Value("${playground.provider.digitalocean.base-image}")
    private String dropletBaseImage;
    @Value("${playground.provider.digitalocean.region}")
    private String appRegion;

    public DigitalOceanProvider(@Qualifier("digitalOceanWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public CreateStorageResponse createStorage(@NonNull String volumeId) {
        Map<String,Object> requestBody = Map.of(
                "name", volumeId,
                "size_gigabytes", 5,
                "region", appRegion,
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
        // 1. Create droplet
        // 2. Attach volume
        Map<String, Object> createDropletRequestPayload = Map.of(
                "name", UUID.randomUUID().toString(),
                "region", appRegion,
                "size", "s-2vcpu-2gb-amd",
                "image", "126259849",
                "type", "snapshots",
                "tags", List.of("playground", "runner", "code-server", "e-leaning", "codercm")
        );
        JsonNode createDropletResponse = webClient.post()
                .uri("/v2/droplets")
                .bodyValue(createDropletRequestPayload)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();
        return CreateRunnerResponse.builder()
                .runnerId(createDropletResponse.get("droplet").get("id").asText())
                .build();
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
