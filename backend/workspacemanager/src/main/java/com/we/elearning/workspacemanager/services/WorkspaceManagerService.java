package com.we.elearning.workspacemanager.services;

import com.we.elearning.workspacemanager.common.dtos.ApiResponse;
import com.we.elearning.workspacemanager.common.dtos.ResponseBuilder;
import com.we.elearning.workspacemanager.dots.WorkspaceDto;
import com.we.elearning.workspacemanager.dots.WorkspaceMapper;
import com.we.elearning.workspacemanager.entities.Workspace;
import com.we.elearning.workspacemanager.entities.WorkspaceStatus;
import com.we.elearning.workspacemanager.repositories.WorkspaceRepository;
import com.we.elearning.workspacemanager.services.providers.ResourceProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkspaceManagerService {
    private final ResourceProvider resourceProvider;
    private final WorkspacePortService workspacePortService;
    private final WorkspaceVolumeService workspaceVolumeService;
    private final WorkspaceRepository workspaceRepository;


    /**
     * This method is used to create a workspace. It takes as input a GitHub repo url that will be used to
     * provision the workspace. It will create a workspace volume and clone the repo into the volume.
     *
     * @param githubRepoUrl the GitHub repo url
     * @return an ApiResponse containing the newly created workspace dto
     */
    public Mono<ApiResponse<WorkspaceDto, Object>> createWorkspace(String githubRepoUrl) {
        return Flux.zip(
                        workspacePortService.getAvailableWorkspacePort(),
                        workspaceVolumeService.createWorkspaceVolume(100)
                ).flatMap(tuple -> {
                    Workspace workspace = resourceProvider
                            .createWorkspace(tuple.getT1(), tuple.getT2().getName(), githubRepoUrl);
                    workspace.setStatus(WorkspaceStatus.RUNNING);
                    return workspaceRepository.save(workspace);
                })
                .map(WorkspaceMapper.INSTANCE::toWorkspaceDto)
                .map(ResponseBuilder::success)
                .last();
    }

    /**
     * This method is used to stop a workspace. It takes as input the workspace id and will stop the workspace
     * and delete the resources used by it.
     *
     * @param workspaceId the workspace id
     * @return a successful ApiResponse with no data
     */
    public Mono<ApiResponse<?, ?>> deleteWorkspace(Long workspaceId) {
        return workspaceRepository.findById(workspaceId)
                .map(workspace -> {
                    //1. Get workspace by workspaceId
                    workspace.setStatus(WorkspaceStatus.STOPPED);
                    //2. Delete workspace
                    resourceProvider.deleteWorkspace(workspace.getRunnerId());
                    //3. Delete workspace
                    workspaceRepository.delete(workspace);
                    return ResponseBuilder.success();
                })
                ;
    }

    /**
     * This method is used to get all workspaces.
     *
     * @return a successful ApiResponse with a list of workspace dtos
     */
    public Mono<ApiResponse<List<WorkspaceDto>, ?>> getAllWorkspaces() {
        return workspaceRepository.findAll()
                .map(WorkspaceMapper.INSTANCE::toWorkspaceDto)
                .collectList()
                .map(ResponseBuilder::success);
    }
}
