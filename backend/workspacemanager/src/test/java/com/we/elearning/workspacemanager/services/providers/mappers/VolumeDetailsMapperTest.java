package com.we.elearning.workspacemanager.services.providers.mappers;

import com.we.elearning.workspacemanager.entities.WorkspaceVolume;
import com.we.elearning.workspacemanager.services.providers.VolumeDetails;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VolumeDetailsMapperTest {

    @Test
    void map() {
        VolumeDetails volumeDetails = VolumeDetails.builder()
                .id(1)
                .name("name")
                .mountPath("mountPath")
                .build();
        WorkspaceVolume map = VolumeDetailsMapper.INSTANCE.map(volumeDetails);
        assertEquals(volumeDetails.getId(), map.getId());
        assertEquals(volumeDetails.getName(), map.getName());
        assertEquals(volumeDetails.getMountPath(), map.getMountPath());
    }

    @Test
    void testMap() {
        WorkspaceVolume workspaceVolume = WorkspaceVolume.builder()
                .id(1L)
                .name("name")
                .mountPath("mountPath")
                .build();
        VolumeDetails map = VolumeDetailsMapper.INSTANCE.map(workspaceVolume);
        assertEquals(workspaceVolume.getId(), map.getId());
        assertEquals(workspaceVolume.getName(), map.getName());
        assertEquals(workspaceVolume.getMountPath(), map.getMountPath());
    }
}