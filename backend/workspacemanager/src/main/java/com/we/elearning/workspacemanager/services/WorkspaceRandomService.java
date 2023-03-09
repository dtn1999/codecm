package com.we.elearning.workspacemanager.services;

import com.we.elearning.workspacemanager.entities.WorkspaceStatus;
import com.we.elearning.workspacemanager.repositories.WorkspaceRepository;
import com.we.elearning.workspacemanager.repositories.WorkspaceVolumeRepository;
import com.we.elearning.workspacemanager.utils.HashUtils;
import com.we.elearning.workspacemanager.utils.RandomUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
public class WorkspaceRandomService {
    private final int MIN_PORT = 9000;
    private final int MAX_PORT = 9999;
    private final int VOLUME_NAME_LENGTH = 10;
    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceVolumeRepository volumeRepository;

    public Mono<Integer> getAvailableWorkspacePort() {
        AtomicBoolean isPortInUse = new AtomicBoolean(true);
        return Mono.just(RandomUtils.getNumber(MIN_PORT, MAX_PORT))
                .repeat()
                .flatMap(port -> Mono.fromCallable(
                                () -> workspaceRepository.existsByPortAndStatus(port, WorkspaceStatus.RUNNING))
                        .subscribeOn(Schedulers.boundedElastic())
                        .map(n -> {
                            isPortInUse.set(n);
                            return port;
                        }))
                .takeUntil(port -> !isPortInUse.get())
                .last();
    }

    public Mono<String> getAvailableWorkspaceVolumeName() {
        AtomicBoolean isNameInUse = new AtomicBoolean(true);
        return Mono.fromCallable(() ->
                        HashUtils.getHash("" + volumeRepository.countAllBy(), VOLUME_NAME_LENGTH))
                .subscribeOn(Schedulers.boundedElastic())
                .repeat()
                .flatMap(name -> Mono.fromCallable(() -> volumeRepository.existsByName(name))
                        .subscribeOn(Schedulers.boundedElastic())
                        .map(n -> {
                            isNameInUse.set(n);
                            return name;
                        }))
                .takeUntil(name -> !isNameInUse.get())
                .last();
    }
}