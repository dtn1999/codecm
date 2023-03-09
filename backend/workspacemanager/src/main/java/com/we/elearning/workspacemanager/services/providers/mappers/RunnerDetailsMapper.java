package com.we.elearning.workspacemanager.services.providers.mappers;

import com.we.elearning.workspacemanager.entities.Workspace;
import com.we.elearning.workspacemanager.services.providers.RunnerDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE, uses = VolumeDetailsMapper.class)
public interface RunnerDetailsMapper {
    RunnerDetailsMapper INSTANCE = Mappers.getMapper(RunnerDetailsMapper.class);

    @Mapping(source = "runnerId", target = "id")
    @Mapping(source = "workspaceVolume", target = "volume")
    RunnerDetails toRunnerDetails(Workspace workspace);

    @Mapping(source = "id", target = "runnerId")
    @Mapping(source = "volume", target = "workspaceVolume")
    @Mapping(target = "id", ignore = true)
    Workspace toWorkspace(RunnerDetails runnerDetails);
}
