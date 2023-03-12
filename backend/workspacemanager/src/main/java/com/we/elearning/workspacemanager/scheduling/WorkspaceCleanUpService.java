package com.we.elearning.workspacemanager.scheduling;

import com.we.elearning.workspacemanager.entities.WorkspaceStatus;
import com.we.elearning.workspacemanager.repositories.WorkspaceRepository;
import com.we.elearning.workspacemanager.services.providers.ResourceProvider;
import com.we.elearning.workspacemanager.services.providers.Runner;
import com.we.elearning.workspacemanager.services.providers.RunnerDetails;
import com.we.elearning.workspacemanager.services.providers.mappers.RunnerDetailsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class WorkspaceCleanUpService {
    private final WorkspaceRepository workspaceRepository;
    private final ResourceProvider resourceProvider;
    private final long idleTimeout;

    public WorkspaceCleanUpService(WorkspaceRepository workspaceRepository,
                                   ResourceProvider resourceProvider,
                                   @Value("${we.elearning.scheduler.idle-timeout}") final long idleTimeout) {
        this.workspaceRepository = workspaceRepository;
        this.resourceProvider = resourceProvider;
        this.idleTimeout = idleTimeout;
    }

    @Scheduled(fixedRate = 5, timeUnit = TimeUnit.MINUTES)
    public void cleanUp() {
        log.info("=======================[ Start:Stopping idle workspaces ]========================");
        workspaceRepository.findAll().forEach(workspace -> {
            log.info("Checking workspace {}", workspace.getId());
            String mountPath = workspace.getWorkspaceVolume().getMountPath();
            String heartbeatPath = String.format("%s/%s", mountPath, "/user-data/heartbeat");
            BasicFileAttributeView fileAttributeView = Files.getFileAttributeView(Path.of(heartbeatPath), BasicFileAttributeView.class);
            try {
                long creationTime = fileAttributeView.readAttributes().creationTime().to(TimeUnit.MINUTES);
                long lastModifiedTime = fileAttributeView.readAttributes().lastModifiedTime().to(TimeUnit.MINUTES);
                if (lastModifiedTime - creationTime > idleTimeout) {
                    log.info("Workspace is idle stopping it.");
                    RunnerDetails runnerDetails = RunnerDetailsMapper.INSTANCE.toRunnerDetails(workspace);
                    Runner runner = resourceProvider.getRunner(runnerDetails);
                    runner.stop();
                    workspace.setStatus(WorkspaceStatus.STOPPED);
                    workspaceRepository.save(workspace);
                    return;
                }
                log.info("Workspace {} is still active", workspace.getId());
            }catch (Exception e){
                log.error("Error while checking workspace {} status", workspace.getId());
                log.error(e.getMessage());
            }
        });
        log.info("=======================[ End:Stopping idle workspaces ]========================");
    }
}
