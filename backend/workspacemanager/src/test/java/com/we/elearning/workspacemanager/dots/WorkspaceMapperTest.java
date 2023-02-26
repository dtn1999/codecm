package com.we.elearning.workspacemanager.dots;

import com.we.elearning.workspacemanager.entities.Workspace;
import com.we.elearning.workspacemanager.entities.WorkspaceStatus;
import com.we.elearning.workspacemanager.entities.WorkspaceVolume;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
class WorkspaceMapperTest {
    long workspaceId = 1;
    String runnerId = "runnerId";
    String host = "host";
    int port = 8080;
    WorkspaceStatus status = WorkspaceStatus.RUNNING;

    long volumeId = 1;
    String name = "name";
    int size = 100;
    @Test
    void toWorkspaceDto() {
        WorkspaceVolume workspaceVolume = WorkspaceVolume.builder()
                .id(volumeId)
                .name(name)
                .size(size)
                .build();

        Workspace workspace = Workspace.builder()
                .id(workspaceId)
                .runnerId(runnerId)
                .host(host)
                .port(port)
                .status(status)
                .workspaceVolume(workspaceVolume)
                .build();

        WorkspaceDto workspaceDto = WorkspaceMapper.INSTANCE.toWorkspaceDto(workspace);
        assertThat(workspaceDto.getId()).isEqualTo(workspaceId);
        assertThat(workspaceDto.getRunnerId()).isEqualTo(runnerId);
        assertThat(workspaceDto.getHost()).isEqualTo(host);
        assertThat(workspaceDto.getPort()).isEqualTo(port);
        assertThat(workspaceDto.getStatus()).isEqualTo(status);
        assertThat(workspaceDto.getWorkspaceVolume().getId()).isEqualTo(volumeId);
        assertThat(workspaceDto.getWorkspaceVolume().getName()).isEqualTo(name);
        assertThat(workspaceDto.getWorkspaceVolume().getSize()).isEqualTo(size);
    }

    @Test
    void tooWorkspace() {
        WorkspaceVolumeDto workspaceVolumeDto = WorkspaceVolumeDto.builder()
                .id(volumeId)
                .name(name)
                .size(size)
                .build();

        WorkspaceDto workspaceDto = WorkspaceDto.builder()
                .id(workspaceId)
                .runnerId(runnerId)
                .host(host)
                .port(port)
                .status(status)
                .workspaceVolume(workspaceVolumeDto)
                .build();

        Workspace workspace = WorkspaceMapper.INSTANCE.toWorkspace(workspaceDto);
        assertThat(workspace.getId()).isEqualTo(workspaceId);
        assertThat(workspace.getRunnerId()).isEqualTo(runnerId);
        assertThat(workspace.getHost()).isEqualTo(host);
        assertThat(workspace.getPort()).isEqualTo(port);
        assertThat(workspace.getStatus()).isEqualTo(status);
        assertThat(workspace.getWorkspaceVolume().getId()).isEqualTo(volumeId);
        assertThat(workspace.getWorkspaceVolume().getName()).isEqualTo(name);
        assertThat(workspace.getWorkspaceVolume().getSize()).isEqualTo(size);
    }
}