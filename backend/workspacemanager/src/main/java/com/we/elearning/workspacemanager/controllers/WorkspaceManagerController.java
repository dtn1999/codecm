package com.we.elearning.workspacemanager.controllers;

import com.we.elearning.workspacemanager.common.dtos.ApiResponse;
import com.we.elearning.workspacemanager.dots.CreateWorkspaceDto;
import com.we.elearning.workspacemanager.dots.WorkspaceDto;
import com.we.elearning.workspacemanager.services.WorkspaceManagerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1/workspacemanager")
@RequiredArgsConstructor
@Slf4j
public class WorkspaceManagerController {
    private final WorkspaceManagerService workspaceManagerService;

    @PostMapping("/workspaces")
    public ResponseEntity<Mono<ApiResponse<WorkspaceDto, ?>>> createWorkspace(@Valid @RequestBody final CreateWorkspaceDto createWorkspaceDto) {
        log.info("createWorkspace: {}", createWorkspaceDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(workspaceManagerService.createWorkspace(createWorkspaceDto.getGithubRepoUrl()));
    }

    @PostMapping("/workspaces/{workspaceId}")
    public ResponseEntity<Mono<ApiResponse<?,?>>> deleteWorkspace(@PathVariable("workspaceId") final Long workspaceId) {
        log.info("deleteWorkspace: {}", workspaceId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(workspaceManagerService.deleteWorkspace(workspaceId));
    }

    @GetMapping("/workspaces")
    public ResponseEntity<Mono<ApiResponse<List<WorkspaceDto>, ?>>> getAllWorkspaces() {
        log.info("getAllWorkspaces");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(workspaceManagerService.getAllWorkspaces());
    }
}
