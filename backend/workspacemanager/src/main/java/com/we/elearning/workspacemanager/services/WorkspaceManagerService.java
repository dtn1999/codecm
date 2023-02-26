package com.we.elearning.workspacemanager.services;

import com.we.elearning.workspacemanager.common.dtos.ApiResponse;
import com.we.elearning.workspacemanager.common.dtos.ResponseBuilder;
import com.we.elearning.workspacemanager.dots.WorkspaceDto;
import com.we.elearning.workspacemanager.dots.WorkspaceMapper;
import com.we.elearning.workspacemanager.entities.Workspace;
import com.we.elearning.workspacemanager.entities.WorkspaceStatus;
import com.we.elearning.workspacemanager.entities.WorkspaceVolume;
import com.we.elearning.workspacemanager.repositories.WorkspaceRepository;
import com.we.elearning.workspacemanager.services.providers.ResourceProvider;
import com.we.elearning.workspacemanager.utils.GitUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkspaceManagerService {
    private final ResourceProvider resourceProvider;
    private final WorkspacePortService workspacePortService;
    private final WorkspaceVolumeService workspaceVolumeService;
    private final WorkspaceRepository workspaceRepository;


    /**
     * TODO: add proper documentation
     * @return
     */
    public ApiResponse<WorkspaceDto, ?> createWorkspace(String githubRepoUrl) {
        //1. Get a random free port for code server
        int availableWorkspacePort = workspacePortService.getAvailableWorkspacePort();
        //2. Create a workspace volume
        WorkspaceVolume workspaceVolume = workspaceVolumeService.createWorkspaceVolume(100);
        Workspace workspace = resourceProvider.createWorkspace(availableWorkspacePort, workspaceVolume.getName(), githubRepoUrl);
        workspace.setStatus(WorkspaceStatus.RUNNING);
        //3. Save workspace to database
        Workspace savedWorkspace = workspaceRepository.save(workspace);
        //4. Return workspace Dto
        return ResponseBuilder.success(WorkspaceMapper.INSTANCE.toWorkspaceDto(savedWorkspace));
    }

    /**
     * TODO: add proper documentation
     * @param workspaceId
     * @return
     */
    public ApiResponse<?, ?> deleteWorkspace(Long workspaceId) {
        //1. Get workspace by workspaceId
        workspaceRepository.findById(workspaceId)
                .ifPresent(workspace -> {
                    workspace.setStatus(WorkspaceStatus.STOPPED);
                    //2. Delete workspace
                    resourceProvider.deleteWorkspace(workspace.getRunnerId());
                    //3. Delete workspace
                    workspaceRepository.delete(workspace);
                });
        return ResponseBuilder.success();
    }

    /**
     * TODO: add proper documentation
     * @return
     */
    public ApiResponse<List<WorkspaceDto>, ?> getAllWorkspaces() {
        List<WorkspaceDto> workspaceDtos = workspaceRepository.findAll()
                .stream()
                .map(WorkspaceMapper.INSTANCE::toWorkspaceDto)
                .toList();
        return ResponseBuilder.success(workspaceDtos);
    }
}
