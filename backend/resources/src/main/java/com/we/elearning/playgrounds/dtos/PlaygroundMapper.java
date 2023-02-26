package com.we.elearning.playgrounds.dtos;

import com.we.elearning.playgrounds.entities.Playground;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface PlaygroundMapper {
    PlaygroundMapper INSTANCE = Mappers.getMapper(PlaygroundMapper.class);
    PlaygroundDto toPlaygroundDto(Playground playground);
    Playground toPlayground(PlaygroundDto playgroundDto);
}
