package com.we.elearning.playgrounds.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.we.elearning.common.dtos.ApiResponse;
import com.we.elearning.common.dtos.ResponseBuilder;
import com.we.elearning.playgrounds.dtos.CreatePlaygroundDto;
import com.we.elearning.playgrounds.dtos.PlaygroundDto;
import com.we.elearning.playgrounds.dtos.PlaygroundMapper;
import com.we.elearning.playgrounds.entities.Playground;
import com.we.elearning.playgrounds.repositories.PlaygroundRepository;
import com.we.elearning.playgrounds.webclients.WorkspaceManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class PlaygroundService {
    private final PlaygroundRepository playgroundRepository;
    private final WorkspaceManagerService workspaceService;
    public PlaygroundService(PlaygroundRepository playgroundRepository,
                             WorkspaceManagerService workspaceService) {
        this.playgroundRepository = playgroundRepository;
        this.workspaceService = workspaceService;
    }

    /**
     * TODO: Implement this method
     *
     * @return
     */
    public ApiResponse getAllPlaygrounds() {
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
    public ApiResponse getPlaygroundById(Long playgroundId) {
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
    public ApiResponse createPlayground(CreatePlaygroundDto createPlaygroundDto) {
        Map<String, Object> createWorkspaceRequest = Map.of(
                "githubRepoUrl", createPlaygroundDto.getGithubRepoUrl()
        );
        return workspaceService.createWorkspace(createWorkspaceRequest)
                .map(response -> {
                    JsonNode responseBody = new ObjectMapper().valueToTree(response.getData());
                    String workspaceHost = responseBody.get("host").asText();
                    int workspacePort = responseBody.get("port").asInt();
                    String workspaceInstanceUrl = String.format("http://%s:%s", workspaceHost, workspacePort);
                    Long workspaceId = responseBody.get("id").asLong();
                    Playground playground = Playground.builder()
                            .name(createPlaygroundDto.getName())
                            .description(createPlaygroundDto.getDescription())
                            .githubRepoUrl(createPlaygroundDto.getGithubRepoUrl())
                            .workspaceId(workspaceId)
                            .instanceUrl(workspaceInstanceUrl)
                            .build();
                    Playground savedPlayground = playgroundRepository.save(playground);
                    return ResponseBuilder.success(PlaygroundMapper.INSTANCE.toPlaygroundDto(savedPlayground));
                })
                .block();
    }


    /**
     * TODO: Implement this method
     *
     * @param playgroundId
     * @return
     */
    public ApiResponse deletePlayground(Long playgroundId) {
        String exceptionMessage = String.format("No playground found with id: %d", playgroundId);
        return playgroundRepository.findById(playgroundId)
                .stream().map(playground -> workspaceService.deleteWorkspace(playground.getWorkspaceId())
                        .map(response -> {
                            if(response.isSuccess()) {
                                playgroundRepository.delete(playground);
                                return ResponseBuilder.success();
                            } else {
                                throw new RuntimeException("Failed to delete workspace");
                            }
                        })
                        .block())
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(exceptionMessage));
    }

    public ApiResponse restorePlayground(Long playgroundId) {
        return null;
    }
}