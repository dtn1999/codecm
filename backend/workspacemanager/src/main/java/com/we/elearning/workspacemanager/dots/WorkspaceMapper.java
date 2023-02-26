package com.we.elearning.workspacemanager.dots;

import com.we.elearning.workspacemanager.entities.Workspace;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = WorkspaceVolumeMapper.class, unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface WorkspaceMapper {
    WorkspaceMapper INSTANCE = Mappers.getMapper(WorkspaceMapper.class);
    WorkspaceDto toWorkspaceDto(Workspace workspace);
    Workspace toWorkspace(WorkspaceDto workspaceDto);
}
