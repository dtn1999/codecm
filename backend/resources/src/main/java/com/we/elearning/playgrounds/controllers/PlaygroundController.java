package com.we.elearning.playgrounds.controllers;

import com.we.elearning.common.dtos.ApiResponse;
import com.we.elearning.playgrounds.dtos.CreatePlaygroundDto;
import com.we.elearning.playgrounds.services.PlaygroundService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/playgrounds")
@RequiredArgsConstructor
@Slf4j
public class PlaygroundController {
    private final PlaygroundService playgroundService;

    @GetMapping("")
    public Mono<ResponseEntity<ApiResponse>> getAllPlaygrounds(@AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) {
        log.info("getting all playgrounds");
        return playgroundService.getAllPlaygroundsForAuthenticatedUser(principal)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/{playgroundId}")
    public Mono<ResponseEntity<ApiResponse>> getPlaygroundById(@PathVariable("playgroundId") final Long playgroundId) {
        log.info("getting playground by id: {}", playgroundId);
        return playgroundService.getPlaygroundById(playgroundId)
                .map(ResponseEntity::ok);
    }

    @PostMapping("")
    public Mono<ResponseEntity<ApiResponse>> createPlayground(@Valid @RequestBody final CreatePlaygroundDto createPlaygroundDto,
                                                              @AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) {
        log.info("creating playground with the following properties: {}", createPlaygroundDto);
        return playgroundService.createPlayground(createPlaygroundDto, principal)
                .map(response -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(response));
    }

    @DeleteMapping("/{playgroundId}")
    public Mono<ResponseEntity<ApiResponse>> deletePlayground(@PathVariable("playgroundId") final Long playgroundId) {
        log.info("deleting playground with id: {}", playgroundId);
        return playgroundService.deletePlayground(playgroundId)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/{playgroundId}/restore")
    public Mono<ResponseEntity<ApiResponse>> restorePlayground(@PathVariable("playgroundId") final Long playgroundId) {
        log.info("restoring playground with id: {}", playgroundId);
        return playgroundService.restorePlayground(playgroundId)
                .map(ResponseEntity::ok);
    }
}
