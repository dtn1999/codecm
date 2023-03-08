package com.we.elearning.playgrounds.controllers;

import com.we.elearning.common.dtos.ApiResponse;
import com.we.elearning.playgrounds.dtos.CreatePlaygroundDto;
import com.we.elearning.playgrounds.dtos.PlaygroundDto;
import com.we.elearning.playgrounds.services.PlaygroundService;
import com.we.elearning.security.Auth0UserInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resources/playgrounds")
@RequiredArgsConstructor
@Slf4j
public class PlaygroundController {
    private final PlaygroundService playgroundService;
    @GetMapping("")
    public ResponseEntity<ApiResponse> getAllPlaygrounds(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal){
        log.info("getting all playgrounds");
        return ResponseEntity.ok(playgroundService.getAllPlaygrounds());
    }

    @GetMapping("/{playgroundId}")
    public ResponseEntity<ApiResponse> getPlaygroundById(@PathVariable("playgroundId") final Long playgroundId) {
        log.info("getting playground by id: {}", playgroundId);
        return ResponseEntity.ok(playgroundService.getPlaygroundById(playgroundId));
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> createPlayground(@Valid @RequestBody final CreatePlaygroundDto createPlaygroundDto) {
        log.info("creating playground with the following properties: {}", createPlaygroundDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(playgroundService.createPlayground(createPlaygroundDto));
    }

    @DeleteMapping("/{playgroundId}")
    public ResponseEntity<ApiResponse> deletePlayground(@PathVariable("playgroundId") final Long playgroundId) {
        log.info("deleting playground with id: {}", playgroundId);
        return ResponseEntity.ok(playgroundService.deletePlayground(playgroundId));
    }

    public ResponseEntity<ApiResponse> restorePlayground(@PathVariable("playgroundId") final Long playgroundId) {
        log.info("restoring playground with id: {}", playgroundId);
        return ResponseEntity.ok(playgroundService.restorePlayground(playgroundId));
    }
}
