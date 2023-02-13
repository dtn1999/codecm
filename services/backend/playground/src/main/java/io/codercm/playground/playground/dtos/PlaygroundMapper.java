package io.codercm.playground.playground.dtos;

import io.codercm.playground.playground.entities.Playground;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlaygroundMapper {
    private final ModelMapper modelMapper;

    public PlaygroundDto toDto(Playground playground) {
        return PlaygroundDto.builder()
                .id(playground.getId())
                .host(playground.getHost())
                .port(playground.getPort())
                .playgroundAppPort(playground.getPlaygroundAppPort())
                .status(playground.getStatus())
                .storage(PlaygroundStorageDto.builder()
                        .id(playground.getStorage().getId())
                        .volumeId(playground.getStorage().getVolumeId())
                        .volumeName(playground.getStorage().getVolumeName())
                        .size(playground.getStorage().getSize())
                        .build())
                .build();
    }

    public Playground toEntity(PlaygroundDto playgroundDto) {
        return modelMapper.map(playgroundDto, Playground.class);
    }
}
