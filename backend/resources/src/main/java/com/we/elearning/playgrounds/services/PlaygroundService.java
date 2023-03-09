package com.we.elearning.playgrounds.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.we.elearning.common.dtos.ApiResponse;
import com.we.elearning.common.dtos.ResponseBuilder;
import com.we.elearning.playgrounds.dtos.CreatePlaygroundDto;
import com.we.elearning.playgrounds.dtos.PlaygroundMapper;
import com.we.elearning.playgrounds.entities.Playground;
import com.we.elearning.playgrounds.exceptions.PlaygroundManagementException;
import com.we.elearning.playgrounds.repositories.PlaygroundRepository;
import com.we.elearning.playgrounds.webclients.WorkspaceManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

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
    public Mono<ApiResponse> getAllPlaygrounds() {
        return Mono.fromCallable(playgroundRepository::findAll)
                .subscribeOn(Schedulers.boundedElastic())
                .map(playgrounds -> playgrounds
                        .stream()
                        .map(PlaygroundMapper.INSTANCE::toPlaygroundDto)
                        .toList()
                )
                .map(ResponseBuilder::success);
    }

    /**
     * Retrieve all playgrounds for the authenticated user
     *
     * @param principal Authenticated user
     * @return List of playgrounds
     */
    public Mono<ApiResponse> getAllPlaygroundsForAuthenticatedUser(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) {
        String userId = principal.getAttribute("sub");
        return Mono.fromCallable(() -> playgroundRepository.findAllByUserId(userId))
                .subscribeOn(Schedulers.boundedElastic())
                .map(playgrounds -> playgrounds
                        .stream()
                        .map(PlaygroundMapper.INSTANCE::toPlaygroundDto)
                        .toList()
                )
                .map(ResponseBuilder::success);
    }

    /**
     * Retrieve a playground by id
     *
     * @param playgroundId ID of the playground
     * @return Playground
     */
    public Mono<ApiResponse> getPlaygroundById(Long playgroundId) {
        String expectedMessage = String.format("No playground found with id: %d", playgroundId);
        return Mono.just(playgroundId)
                .map(playgroundRepository::findById)
                .subscribeOn(Schedulers.boundedElastic())
                .switchIfEmpty(Mono.error(new NoSuchElementException(expectedMessage)))
                .map(playground -> playground.stream()
                        .map(PlaygroundMapper.INSTANCE::toPlaygroundDto)
                        .map(ResponseBuilder::success)
                        .findAny()
                        .orElseThrow()
                );
    }


    /**
     * Create a new playground for the authenticated user
     *
     * @param createPlaygroundDto Playground data
     * @param principal           Authenticated user
     * @return created Playground
     */
    @Transactional
    public Mono<ApiResponse> createPlayground(CreatePlaygroundDto createPlaygroundDto,
                                              @AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) {
        Map<String, Object> createWorkspaceRequest = Map.of(
                "githubRepoUrl", createPlaygroundDto.getGithubRepoUrl()
        );
        return workspaceService.createWorkspace(createWorkspaceRequest)
                .flatMap(response -> {
                    if (response.isSuccess()) {
                        return handleSuccessResponse(principal, createPlaygroundDto, response);
                    } else {
                        return Mono.error(new PlaygroundManagementException("Failed to create workspace"));
                    }
                })
                .onErrorMap(throwable -> new PlaygroundManagementException("Failed to create workspace"))
                ;
    }


    /**
     * Delete a playground by id
     *
     * @param playgroundId ID of the playground
     * @return ApiResponse
     */
    public Mono<ApiResponse> deletePlayground(Long playgroundId) {
        String exceptionMessage = String.format("No playground found with id: %d", playgroundId);
        return Mono.fromCallable(() -> playgroundRepository.findById(playgroundId))
                .subscribeOn(Schedulers.boundedElastic())
                .switchIfEmpty(Mono.error(new NoSuchElementException(exceptionMessage)))
                .flatMap(playground -> workspaceService.deleteWorkspace(playground.orElseThrow().getWorkspaceId()))
                .map(response -> {
                    if (response.isSuccess()) {
                        playgroundRepository.deleteById(playgroundId);
                        return ResponseBuilder.success();
                    } else {
                        throw new RuntimeException("Failed to delete workspace");
                    }
                })
                .onErrorMap(throwable -> new PlaygroundManagementException("Failed to delete workspace"));
    }

    public ApiResponse restorePlayground(Long playgroundId) {
        log.info("Restore playground with id: {}", playgroundId);
        return null;
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public Mono<ApiResponse> handleSuccessResponse(OAuth2AuthenticatedPrincipal principal,
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
                .imageUrl(request.getImageUrl())
                .userId(principal.getAttribute("sub"))
                .build();
        return Mono.fromCallable(() -> playgroundRepository.save(playground))
                .subscribeOn(Schedulers.boundedElastic())
                .map(savedPlayground ->
                        ResponseBuilder.success(PlaygroundMapper
                                .INSTANCE
                                .toPlaygroundDto(savedPlayground))
                );
    }

}