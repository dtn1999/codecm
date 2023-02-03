package io.codercm.playground.playground.services;

import io.codercm.playground.playground.dtos.PlaygroundDto;
import io.codercm.playground.playground.dtos.PlaygroundStorageDto;
import io.codercm.playground.playground.entities.Playground;
import io.codercm.playground.playground.entities.PlaygroundStatus;
import io.codercm.playground.playground.entities.PlaygroundStorage;
import io.codercm.playground.playground.repositories.PlaygroundRepository;
import io.codercm.playground.playground.repositories.PlaygroundStorageRepository;
import io.codercm.playground.providers.PortMonitor;
import io.codercm.playground.providers.ResourceProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaygroundService {
    private final PlaygroundRepository playgroundRepository;
    private final PlaygroundStorageRepository storageRepository;
    private final ResourceProvider resourceProvider;
    private final PortMonitor portMonitor;

    public PlaygroundDto createPlayground() {
        PlaygroundStorage playgroundStorage = storageRepository.save(PlaygroundStorage.builder().build());
        int port = portMonitor.getRandomPortForCodeServer();
        while (playgroundRepository
                .findAllByPort(port)
                .stream()
                .anyMatch(playground-> playground.getStatus().equals(PlaygroundStatus.RUNNING))){
            port = portMonitor.getRandomPortForCodeServer();
        }
        int playgroundAppPort = portMonitor.getRandomPortForPlaygroundApp();
        while (playgroundRepository
                .findAllByPlaygroundAppPort(playgroundAppPort)
                .stream()
                .anyMatch(playground-> playground.getStatus().equals(PlaygroundStatus.RUNNING))){
            playgroundAppPort = portMonitor.getRandomPortForPlaygroundApp();
        }
        PlaygroundStorageDto storage = resourceProvider.createStorage(playgroundStorage.getVolumeName());
        playgroundStorage.setVolumeId(storage.getVolumeId());
        PlaygroundDto playground = resourceProvider.createPlayground(playgroundStorage.getVolumeName(), port, playgroundAppPort);
        return null;
    }

}
