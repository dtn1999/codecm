package io.codercm.playground.providers.linode;

import com.fasterxml.jackson.databind.JsonNode;
import io.codercm.playground.playground.dtos.PlaygroundDto;
import io.codercm.playground.providers.ResourceProvider;
import io.codercm.playground.providers.entities.CreateRunnerResponse;
import io.codercm.playground.providers.entities.CreateStorageResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("linodeResourceProvider")
public class LinodeResourceProvider implements ResourceProvider {
    private final WebClient linodeWebClient;
    @Value("${playground.provider.linode.region}")
    private String linodeRegion;

    public LinodeResourceProvider(@Qualifier("linodeWebClient") WebClient linodeWebClient) {
        this.linodeWebClient = linodeWebClient;
    }

    @Override
    public CreateStorageResponse createStorage(@NonNull String volumeId) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("label", "my-volume");
        requestBody.put("size", 20);
        requestBody.put("region", linodeRegion);
        try{
            ResponseEntity<JsonNode> response = linodeWebClient.post()
                    .uri("/v4/volumes")
                    .body(BodyInserters.fromValue(requestBody))
                    .retrieve()
                    .toEntity(JsonNode.class)
                    .block();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
