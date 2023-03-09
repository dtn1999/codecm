package com.we.elearning.workspacemanager.controllers;

import com.we.elearning.workspacemanager.common.dtos.ApiResponse;
import com.we.elearning.workspacemanager.dots.CreateWorkspaceDto;
import com.we.elearning.workspacemanager.services.WorkspaceManagerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class WorkspaceManagerController {
    private final WorkspaceManagerService workspaceManagerService;

    @PostMapping("/workspaces")
    public Mono<ResponseEntity<ApiResponse>> createWorkspace(@Valid @RequestBody final CreateWorkspaceDto createWorkspaceDto) {
        log.info("====================[ Start:createWorkspace: {} ]=====================", createWorkspaceDto);
        return workspaceManagerService
                .createWorkspace(createWorkspaceDto.getGithubRepoUrl())
                .map(ResponseEntity::ok)
                .doOnTerminate(() -> log.info("====================[ end:createWorkspace: {} ]=====================", createWorkspaceDto))
                ;
    }

    @DeleteMapping("/workspaces/{workspaceId}")
    public Mono<ResponseEntity<ApiResponse>> deleteWorkspace(@PathVariable("workspaceId") final Long workspaceId) {
        log.info("======================[ start:deleteWorkspace: {} ]==========================", workspaceId);
        return workspaceManagerService.deleteWorkspace(workspaceId)
                .map(ResponseEntity::ok)
                .doOnTerminate(() -> log.info("======================[ end:deleteWorkspace: {} ]==========================", workspaceId));


    }

    @GetMapping("/workspaces")
    public Mono<ResponseEntity<ApiResponse>> getAllWorkspaces() {
        log.info("======================[ start:getAllWorkspaces ]==========================");
        return workspaceManagerService.getAllWorkspaces()
                .map(ResponseEntity::ok)
                .doOnTerminate(() -> log.info("======================[ end:getAllWorkspaces ]=========================="));
    }
}
