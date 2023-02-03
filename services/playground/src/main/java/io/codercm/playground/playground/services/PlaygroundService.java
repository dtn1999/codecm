package io.codercm.playground.playground.services;

import io.codercm.playground.playground.dtos.PlaygroundDto;
import io.codercm.playground.playground.dtos.PlaygroundMapper;
import io.codercm.playground.playground.entities.Playground;
import io.codercm.playground.playground.entities.PlaygroundPortConfig;
import io.codercm.playground.playground.entities.PlaygroundStatus;
import io.codercm.playground.playground.entities.PlaygroundStorage;
import io.codercm.playground.playground.repositories.PlaygroundRepository;
import io.codercm.playground.playground.repositories.PlaygroundStorageRepository;
import io.codercm.playground.providers.PortMonitor;
import io.codercm.playground.providers.ResourceProvider;
import io.codercm.playground.providers.entities.CreateRunnerResponse;
import io.codercm.playground.providers.entities.CreateStorageResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlaygroundService {
    private final PlaygroundRepository playgroundRepository;
    private final PlaygroundStorageRepository storageRepository;
    private final ResourceProvider resourceProvider;
    private final PortMonitor portMonitor;
    private final PlaygroundMapper playgroundMapper;

    public PlaygroundService(PlaygroundRepository playgroundRepository, PlaygroundStorageRepository storageRepository,
                             @Qualifier("dockerResourceProvider") ResourceProvider resourceProvider, PortMonitor portMonitor,
                             PlaygroundMapper playgroundMapper) {
        this.playgroundRepository = playgroundRepository;
        this.storageRepository = storageRepository;
        this.resourceProvider = resourceProvider;
        this.portMonitor = portMonitor;
        this.playgroundMapper = playgroundMapper;
    }

    /**
     * @return
     */
    public PlaygroundDto createPlayground() {
        PlaygroundStorage playgroundStorage = storageRepository.save(PlaygroundStorage.builder().build());
        PlaygroundPortConfig portConfig = getPortConfig();
        CreateStorageResponse storage = resourceProvider.createStorage(playgroundStorage.getId().toString());
        playgroundStorage.setVolumeId(storage.getVolumeId());
        playgroundStorage.setVolumeName(storage.getVolumeName());
        CreateRunnerResponse providerResponse = resourceProvider
                .createPlayground(playgroundStorage.getVolumeName(),
                        portConfig.getCodeServerPort(),
                        portConfig.getPlaygroundAppPort());
        Playground playground = playgroundRepository.save(Playground.builder()
                .host(providerResponse.getHost())
                .port(providerResponse.getPort())
                .playgroundAppPort(portConfig.getPlaygroundAppPort())
                .runnerId(providerResponse.getRunnerId())
                .status(PlaygroundStatus.RUNNING)
                .storage(storageRepository.save(playgroundStorage))
                .build());
        resourceProvider.startPlayground(playground.getRunnerId());
        return playgroundMapper.toDto(playground);
    }

    /**
     * @return
     */
    public List<PlaygroundDto> getAllPlaygrounds() {
        return playgroundRepository.findAll().stream()
                .map(playgroundMapper::toDto)
                .toList();
    }

    public PlaygroundDto restorePlayground(String playgroundId) {
        return playgroundRepository.findById(UUID.fromString(playgroundId)).stream().map(playground -> {
            PlaygroundPortConfig portConfig = getPortConfig();
            CreateRunnerResponse providerResponse = resourceProvider
                    .createPlayground(playground.getStorage().getVolumeName(),
                            portConfig.getCodeServerPort(),
                            portConfig.getPlaygroundAppPort());
            playground.setStatus(PlaygroundStatus.RUNNING);
            playground.setHost(providerResponse.getHost());
            playground.setPort(providerResponse.getPort());
            playground.setPlaygroundAppPort(portConfig.getPlaygroundAppPort());
            playground.setRunnerId(providerResponse.getRunnerId());
            playgroundRepository.save(playground);
            resourceProvider.startPlayground(playground.getRunnerId());
            return playgroundMapper.toDto(playground);
        }).toList().get(0);
    }

    private PlaygroundPortConfig getPortConfig() {
        int port = portMonitor.getRandomPortForCodeServer();
        while (playgroundRepository
                .findAllByPort(port)
                .stream()
                .anyMatch(playground -> playground.getStatus().equals(PlaygroundStatus.RUNNING))) {
            port = portMonitor.getRandomPortForCodeServer();
        }
        int playgroundAppPort = portMonitor.getRandomPortForPlaygroundApp();
        while (playgroundRepository
                .findAllByPlaygroundAppPort(playgroundAppPort)
                .stream()
                .anyMatch(playground -> playground.getStatus().equals(PlaygroundStatus.RUNNING))) {
            playgroundAppPort = portMonitor.getRandomPortForPlaygroundApp();
        }
        return PlaygroundPortConfig.builder()
                .codeServerPort(port)
                .playgroundAppPort(playgroundAppPort)
                .build();
    }
}
