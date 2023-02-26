package com.we.elearning.playgrounds.controllers;

import com.we.elearning.common.dtos.ApiResponse;
import com.we.elearning.playgrounds.dtos.CreatePlaygroundDto;
import com.we.elearning.playgrounds.dtos.PlaygroundDto;
import com.we.elearning.playgrounds.services.PlaygroundService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/resources/playgrounds")
@RequiredArgsConstructor
public class PlaygroundController {
    private final PlaygroundService playgroundService;

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<PlaygroundDto>, ?>> getAllPlaygrounds() {
        return ResponseEntity.ok(playgroundService.getAllPlaygrounds());
    }

    @GetMapping("/{playgroundId}")
    public ResponseEntity<ApiResponse<PlaygroundDto, ?>> getPlaygroundById(@PathVariable("playgroundId") final Long playgroundId) {
        return ResponseEntity.ok(playgroundService.getPlaygroundById(playgroundId));
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<PlaygroundDto, ?>> createPlayground(@Valid @RequestBody final CreatePlaygroundDto createPlaygroundDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(playgroundService.createPlayground(createPlaygroundDto));
    }

    @DeleteMapping("/{playgroundId}")
    public ResponseEntity<ApiResponse<?, ?>> deletePlayground(@PathVariable("playgroundId") final Long playgroundId) {
        return ResponseEntity.ok(playgroundService.deletePlayground(playgroundId));
    }
}
