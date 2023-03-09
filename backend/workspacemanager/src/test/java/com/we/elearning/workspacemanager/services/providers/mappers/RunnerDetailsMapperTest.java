package com.we.elearning.workspacemanager.services.providers.mappers;

import com.we.elearning.workspacemanager.entities.Workspace;
import com.we.elearning.workspacemanager.entities.WorkspaceVolume;
import com.we.elearning.workspacemanager.services.providers.RunnerDetails;
import com.we.elearning.workspacemanager.services.providers.VolumeDetails;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RunnerDetailsMapperTest {

    @Test
    void map() {
        RunnerDetails details = RunnerDetails.builder()
                .id("id")
                .name("name")
                .host("host")
                .port(1)
                .volume(VolumeDetails.builder()
                        .name("name")
                        .mountPath("mountPath")
                        .id(1L)
                        .size(1L)
                        .build())
                .build();
        Workspace map = RunnerDetailsMapper.INSTANCE.toWorkspace(details);
        assertThat(map.getRunnerId()).isEqualTo(details.getId());
        assertThat(map.getHost()).isEqualTo(details.getHost());
        assertThat(map.getPort()).isEqualTo(details.getPort());
        assertThat(map.getWorkspaceVolume().getName()).isEqualTo(details.getVolume().getName());
        assertThat(map.getWorkspaceVolume().getMountPath()).isEqualTo(details.getVolume().getMountPath());
        assertThat(map.getWorkspaceVolume().getId()).isEqualTo(details.getVolume().getId());
        assertThat(map.getWorkspaceVolume().getSize()).isEqualTo(details.getVolume().getSize());
    }

    @Test
    void testMap() {
        Workspace workspace = Workspace.builder()
                .runnerId("id")
                .host("host")
                .port(1)
                .workspaceVolume(WorkspaceVolume.builder()
                        .name("name")
                        .mountPath("mountPath")
                        .id(1L)
                        .size(1L)
                        .build())
                .build();
        RunnerDetails map = RunnerDetailsMapper.INSTANCE.toRunnerDetails(workspace);
        assertThat(map.getId()).isEqualTo(workspace.getRunnerId());
        assertThat(map.getHost()).isEqualTo(workspace.getHost());
        assertThat(map.getPort()).isEqualTo(workspace.getPort());
        assertThat(map.getVolume().getName()).isEqualTo(workspace.getWorkspaceVolume().getName());
        assertThat(map.getVolume().getMountPath()).isEqualTo(workspace.getWorkspaceVolume().getMountPath());
        assertThat(map.getVolume().getId()).isEqualTo(workspace.getWorkspaceVolume().getId());
        assertThat(map.getVolume().getSize()).isEqualTo(workspace.getWorkspaceVolume().getSize());
    }
}