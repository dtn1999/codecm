package com.we.elearning.workspacemanager.services;

import com.we.elearning.workspacemanager.common.dtos.ApiResponse;
import com.we.elearning.workspacemanager.common.dtos.ResponseBuilder;
import com.we.elearning.workspacemanager.dots.WorkspaceMapper;
import com.we.elearning.workspacemanager.entities.Workspace;
import com.we.elearning.workspacemanager.entities.WorkspaceStatus;
import com.we.elearning.workspacemanager.repositories.WorkspaceRepository;
import com.we.elearning.workspacemanager.services.providers.CreateWorkspaceRequest;
import com.we.elearning.workspacemanager.services.providers.ResourceProvider;
import com.we.elearning.workspacemanager.services.providers.Runner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkspaceManagerService {
    private final ResourceProvider resourceProvider;
    private final WorkspaceRandomService workspaceRandomService;
    private final WorkspaceRepository workspaceRepository;


    /**
     * This method is used to create a workspace. It takes as input a GitHub repo url that will be used to
     * provision the workspace. It will create a workspace volume and clone the repo into the volume.
     *
     * @param githubRepoUrl the GitHub repo url
     * @return an ApiResponse containing the newly created workspace dto
     */
    public Mono<ApiResponse> createWorkspace(String githubRepoUrl) {
        log.info("Creating workspace for repo: {}", githubRepoUrl);
        return Mono.zip(
                workspaceRandomService.getAvailableWorkspacePort(),
                workspaceRandomService.getAvailableWorkspaceVolumeName()
        ).flatMap(tuple -> {
            CreateWorkspaceRequest createRequest = CreateWorkspaceRequest.builder()
                    .githubRepoUrl(githubRepoUrl)
                    .codeServerPort(tuple.getT1())
                    .volumeName(tuple.getT2())
                    .build();
            Runner runner = resourceProvider.createWorkspace(createRequest);
            runner.getDetails();
            Workspace workspace = new Workspace();
//            workspace.setWorkspaceVolume(tuple.getT2());
            workspace.setStatus(WorkspaceStatus.RUNNING);
            log.info("Workspace created successfully");
            return Mono.fromCallable(() -> workspaceRepository.save(workspace))
                    .subscribeOn(Schedulers.boundedElastic())
                    .map(savedWorkspace -> {
                        log.info("Workspace saved successfully");
//                        tuple.getT2().setWorkspace(workspace);
                        return ResponseBuilder.success(WorkspaceMapper.INSTANCE.toWorkspaceDto(savedWorkspace));
                    })
                    ;
        });
    }

    /**
     * This method is used to stop a workspace. It takes as input the workspace id and will stop the workspace
     * and delete the resources used by it.
     *
     * @param workspaceId the workspace id
     * @return a successful ApiResponse with no data
     */
    public Mono<ApiResponse> deleteWorkspace(Long workspaceId) {
        log.info("Deleting workspace with id: {}", workspaceId);
        return Mono.fromCallable(() -> workspaceRepository.findById(workspaceId))
                .subscribeOn(Schedulers.boundedElastic())
                .switchIfEmpty(Mono.error(new NoSuchElementException("Workspace not found")))
                .map(workspace -> workspace.map(foundWorkspace -> {
                            //1. Get workspace by workspaceId
                            foundWorkspace.setStatus(WorkspaceStatus.STOPPED);
                            //2. Delete workspace
//                            resourceProvider.deleteWorkspace(foundWorkspace.getRunnerId(), foundWorkspace.getWorkspaceVolume().getName());
                            //3. Delete workspace
                            workspaceRepository.delete(foundWorkspace);
                            log.info("Workspace deleted successfully");
                            return ResponseBuilder.success();
                        }).orElseThrow()
                );
    }

    /**
     * This method is used to get all workspaces.
     *
     * @return a successful ApiResponse with a list of workspace dtos
     */
    public Mono<ApiResponse> getAllWorkspaces() {
        return Flux.fromIterable(workspaceRepository.findAll())
                .map(WorkspaceMapper.INSTANCE::toWorkspaceDto)
                .collectList()
                .map(ResponseBuilder::success);
    }
}