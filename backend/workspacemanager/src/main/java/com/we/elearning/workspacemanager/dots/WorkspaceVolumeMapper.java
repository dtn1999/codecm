package com.we.elearning.workspacemanager.dots;

import com.we.elearning.workspacemanager.entities.WorkspaceVolume;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface WorkspaceVolumeMapper {
    WorkspaceVolumeMapper INSTANCE = Mappers.getMapper(WorkspaceVolumeMapper.class);

    WorkspaceVolumeDto toWorkspaceVolumeDto(WorkspaceVolume workspaceVolume);

    WorkspaceVolume toWorkspaceVolume(WorkspaceVolumeDto workspaceVolumeDto);
}
