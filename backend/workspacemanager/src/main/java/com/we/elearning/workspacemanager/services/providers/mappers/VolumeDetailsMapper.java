package com.we.elearning.workspacemanager.services.providers.mappers;

import com.we.elearning.workspacemanager.entities.WorkspaceVolume;
import com.we.elearning.workspacemanager.services.providers.VolumeDetails;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface VolumeDetailsMapper {
    VolumeDetailsMapper INSTANCE = Mappers.getMapper(VolumeDetailsMapper.class);
    VolumeDetails map(WorkspaceVolume workspaceVolume);
    WorkspaceVolume map(VolumeDetails volumeDetails);
}
