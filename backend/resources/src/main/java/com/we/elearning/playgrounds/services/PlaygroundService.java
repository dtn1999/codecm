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
import com.we.elearning.security.Auth0UserInfoDto;
import com.we.elearning.security.OAuth2AuthenticatedPrincipalImpl;
import com.we.elearning.security.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

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
     * Retrieve all playgrounds not matter the authenticated user
     * TODO: Make this method only works for user with admin role
     *
     * @return List of playgrounds
     */
    public ApiResponse getAllPlaygrounds() {
        List<PlaygroundDto> playgroundDtos = playgroundRepository.findAll()
                .stream()
                .map(PlaygroundMapper.INSTANCE::toPlaygroundDto)
                .toList();
        return ResponseBuilder.success(playgroundDtos);
    }

    /**
     * Retrieve all playgrounds for the authenticated user
     *
     * @param principal
     * @return List of playgrounds
     */
    public ApiResponse getAllPlaygroundsForAuthenticatedUser(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) {
        String userId = principal.getAttribute("sub");
        List<PlaygroundDto> playgroundDtos = playgroundRepository.findAllByUserId(userId)
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
    @Transactional
    public Mono<ApiResponse> createPlayground(CreatePlaygroundDto createPlaygroundDto,
                                              @AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) {
        Map<String, Object> createWorkspaceRequest = Map.of(
                "githubRepoUrl", createPlaygroundDto.getGithubRepoUrl()
        );
        return workspaceService.createWorkspace(createWorkspaceRequest)
                .publishOn(Schedulers.boundedElastic())
                .map(response -> {
                    if (response.isSuccess()) {
                        return handleSuccessResponse((OAuth2AuthenticatedPrincipalImpl) principal, createPlaygroundDto, response);
                    } else {
                        throw new RuntimeException("Failed to create workspace");
                    }
                })
                .onErrorMap(throwable -> {
                    return new RuntimeException("Failed to create workspace");
                })
                ;
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
                            if (response.isSuccess()) {
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

    @Transactional(rollbackFor = RuntimeException.class)
    public ApiResponse handleSuccessResponse(OAuth2AuthenticatedPrincipalImpl principal,
                                             CreatePlaygroundDto request, ApiResponse response) {
        JsonNode responseBody = new ObjectMapper().valueToTree(response.getData());
        String workspaceHost = responseBody.get("host").asText();
        int workspacePort = responseBody.get("port").asInt();
        String workspaceInstanceUrl = String.format("http://%s:%s", workspaceHost, workspacePort);
        Long workspaceId = responseBody.get("id").asLong();
        Playground playground = Playground.builder()
                .name(request.getName())
                .description(request.getDescription())
                .githubRepoUrl(request.getGithubRepoUrl())
                .workspaceId(workspaceId)
                .instanceUrl(workspaceInstanceUrl)
                .userId(principal.getAttribute("sub"))
                .build();
        Playground savedPlayground = playgroundRepository.save(playground);
        return ResponseBuilder.success(PlaygroundMapper.INSTANCE.toPlaygroundDto(savedPlayground));
    }

}