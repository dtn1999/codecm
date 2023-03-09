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
        log.info("========================[ start:getting all playgrounds ]========================");
        return playgroundService.getAllPlaygroundsForAuthenticatedUser(principal)
                .map(ResponseEntity::ok)
                .doOnSuccess(responseEntity -> log.info("========================[ end:getting all playgrounds ]========================"));
    }

    @GetMapping("/{playgroundId}")
    public Mono<ResponseEntity<ApiResponse>> getPlaygroundById(@PathVariable("playgroundId") final Long playgroundId) {
        log.info("========================[ start:getting playground with id: {} ]===========================", playgroundId);
        return playgroundService.getPlaygroundById(playgroundId)
                .map(ResponseEntity::ok)
                .doOnSuccess(responseEntity -> log.info("========================[ end:getting playground with id: {} ]===========================", playgroundId));
    }

    @PostMapping("")
    public Mono<ResponseEntity<ApiResponse>> createPlayground(@Valid @RequestBody final CreatePlaygroundDto createPlaygroundDto,
                                                              @AuthenticationPrincipal OAuth2AuthenticatedPrincipal principal) {
        log.info("=========================[ start:creating playground with name: {} ]=================================", createPlaygroundDto.getName());
        return playgroundService.createPlayground(createPlaygroundDto, principal)
                .map(response -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(response))
                .doOnSuccess(responseEntity -> log.info("=========================[ end:creating playground with name: {} ]=================================", createPlaygroundDto.getName()));
    }

    @DeleteMapping("/{playgroundId}")
    public Mono<ResponseEntity<ApiResponse>> deletePlayground(@PathVariable("playgroundId") final Long playgroundId) {
        log.info("================================[ start:deleting playground with id: {} ]================================", playgroundId);
        return playgroundService.deletePlayground(playgroundId)
                .map(ResponseEntity::ok)
                .doOnSuccess(responseEntity -> log.info("================================[ end:deleting playground with id: {} ]================================", playgroundId));
    }

    @PutMapping("/{playgroundId}/restore")
    public Mono<ResponseEntity<ApiResponse>> restorePlayground(@PathVariable("playgroundId") final Long playgroundId) {
        log.info("================================[ start:restoring playground with id: {} ]================================", playgroundId);
        return playgroundService.restorePlayground(playgroundId)
                .map(ResponseEntity::ok)
                .doOnSuccess(responseEntity -> log.info("================================[ end:restoring playground with id: {} ]================================", playgroundId));
    }
}
