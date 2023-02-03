package io.codercm.playground.playground.services;

import io.codercm.playground.playground.dtos.PlaygroundDto;
import io.codercm.playground.playground.repositories.PlaygroundRepository;
import io.codercm.playground.providers.ResourceProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaygroundService {
    private final PlaygroundRepository playgroundRepository;
    private final ResourceProvider resourceProvider;

    public PlaygroundDto createPlayground(PlaygroundDto playgroundDto) {
        return null;
    }

}
