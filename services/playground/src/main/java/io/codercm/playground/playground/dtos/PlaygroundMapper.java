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
        return modelMapper.map(playground, PlaygroundDto.class);
    }

    public Playground toEntity(PlaygroundDto playgroundDto) {
        return modelMapper.map(playgroundDto, Playground.class);
    }
}
