package com.we.elearning.playgrounds.services;

import com.we.elearning.common.dtos.ApiResponse;
import com.we.elearning.common.dtos.ResponseBuilder;
import com.we.elearning.playgrounds.dtos.CreatePlaygroundDto;
import com.we.elearning.playgrounds.dtos.PlaygroundDto;
import com.we.elearning.playgrounds.dtos.PlaygroundMapper;
import com.we.elearning.playgrounds.entities.Playground;
import com.we.elearning.playgrounds.exceptions.PlaygroundNotCreatedException;
import com.we.elearning.playgrounds.repositories.PlaygroundRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@Slf4j
public class PlaygroundService {
    private final PlaygroundRepository playgroundRepository;
    private final WebClient workspaceManagerWebClient;

    public PlaygroundService(PlaygroundRepository playgroundRepository,
                             @Qualifier("workspaceManagerWebClient") WebClient workspaceManagerWebClient) {
        this.playgroundRepository = playgroundRepository;
        this.workspaceManagerWebClient = workspaceManagerWebClient;
    }

    /**
     * TODO: Implement this method
     *
     * @return
     */
    public ApiResponse<List<PlaygroundDto>, ?> getAllPlaygrounds() {
        List<PlaygroundDto> playgroundDtos = playgroundRepository.findAll()
                .stream()
                .map(PlaygroundMapper.INSTANCE::toPlaygroundDto)
                .toList();
        return ResponseBuilder.success(playgroundDtos);
    }

    /**
     * TODO: Implement this method
     *
     * @param playgroundId
     * @return
     */
    public ApiResponse<PlaygroundDto, ?> getPlaygroundById(Long playgroundId) {
        String expectedMessage = String.format("No playground found with id: %d", playgroundId);
        PlaygroundDto playgroundDto = playgroundRepository.findById(playgroundId)
                .map(PlaygroundMapper.INSTANCE::toPlaygroundDto)
                .orElseThrow(() -> new NoSuchElementException(expectedMessage));
        return ResponseBuilder.success(playgroundDto);
    }


    /**
     * TODO: Implement this method
     *
     * @param createPlaygroundDto
     * @return
     */
    public ApiResponse<PlaygroundDto, ?> createPlayground(CreatePlaygroundDto createPlaygroundDto) {
        Map<String, Object> createWorkspaceRequest = Map.of(
                "githubRepoUrl", createPlaygroundDto.getGithubRepoUrl()
        );
        ResponseEntity<ApiResponse<Map<String, Object>, Object>> createWorkspaceResponse;
        try {
            createWorkspaceResponse = workspaceManagerWebClient.post()
                    .uri("/api/v1/workspacemanager/workspaces")
                    .bodyValue(createWorkspaceRequest)
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<ApiResponse<Map<String, Object>, Object>>() {
                    })
                    .block();
        } catch (Exception e) {
            log.error("Failed to create workspace", e);
            throw new PlaygroundNotCreatedException("Failed to create workspace");
        }
        if (Objects.nonNull(createWorkspaceResponse) && createWorkspaceResponse.getStatusCode().is2xxSuccessful()) {
            String workspaceHost = (String) createWorkspaceResponse.getBody().getData().get("host");
            int workspacePort = (int) createWorkspaceResponse.getBody().getData().get("port");
            String workspaceInstanceUrl = String.format("http://%s:%s", workspaceHost, workspacePort);
            Long workspaceId = createWorkspaceResponse.getBody().getData().get("id") instanceof Integer ?
                    ((Integer) createWorkspaceResponse.getBody().getData().get("id")).longValue() :
                    (Long) createWorkspaceResponse.getBody().getData().get("id");

            Playground playground = Playground.builder()
                    .name(createPlaygroundDto.getName())
                    .description(createPlaygroundDto.getDescription())
                    .githubRepoUrl(createPlaygroundDto.getGithubRepoUrl())
                    .workspaceId(workspaceId)
                    .instanceUrl(workspaceInstanceUrl)
                    .build();
            PlaygroundDto playgroundDto = PlaygroundMapper.INSTANCE.toPlaygroundDto(playgroundRepository.save(playground));
            return ResponseBuilder.success(playgroundDto);
        }else {
            throw new PlaygroundNotCreatedException("Failed to create workspace");
        }
    }


    /**
     * TODO: Implement this method
     *
     * @param playgroundId
     * @return
     */
    public ApiResponse<?, ?> deletePlayground(Long playgroundId) {
        String exceptionMessage = String.format("No playground found with id: %d", playgroundId);
        return playgroundRepository.findById(playgroundId)
                .stream().map(playground -> {
                    ResponseEntity<Map<String, Object>> deleteWorkspaceResponse = workspaceManagerWebClient.delete()
                            .uri("/api/v1/workspacemanager/workspaces/{workspaceId}", playground.getWorkspaceId())
                            .retrieve()
                            .toEntity(new ParameterizedTypeReference<Map<String, Object>>() {
                            })
                            .block();
                    if (Objects.nonNull(deleteWorkspaceResponse) && deleteWorkspaceResponse.getStatusCode().is2xxSuccessful()) {
                        playgroundRepository.delete(playground);
                        return ResponseBuilder.success();
                    } else {
                        throw new RuntimeException("Failed to delete workspace");
                    }
                })
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(exceptionMessage));
    }
}