package io.codercm.playground.playground.controllers;

import io.codercm.playground.common.entities.ResponsePayload;
import io.codercm.playground.playground.dtos.PlaygroundDto;
import io.codercm.playground.playground.services.PlaygroundService;
import io.codercm.playground.providers.digitalocean.DigitalOceanProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/playgrounds")
public class PlaygroundController {
    private final PlaygroundService playgroundService;
    private final DigitalOceanProvider digitalOceanProvider;
    public PlaygroundController(PlaygroundService playgroundService,
                                @Qualifier("digitalOceanResourceProvider") DigitalOceanProvider digitalOceanProvider) {
        this.playgroundService = playgroundService;
        this.digitalOceanProvider = digitalOceanProvider;
    }

    @GetMapping("/digitalocean")
    public ResponseEntity<?> digitalOcean() {
       return ResponseEntity.ok(digitalOceanProvider.createPlayground("test", 8080, 8081));
    }
    @GetMapping("/")
    public ResponseEntity<ResponsePayload<List<PlaygroundDto>, ?>> getAllPlaygrounds() {
        return ResponseEntity.ok(
                ResponsePayload.<List<PlaygroundDto>, Void>builder()
                        .data(playgroundService.getAllPlaygrounds())
                        .build()
        );
    }

    @PostMapping("/")
    public ResponseEntity<ResponsePayload<PlaygroundDto, ?>> createPlayground() {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponsePayload.<PlaygroundDto, Void>builder()
                        .data(playgroundService.createPlayground())
                        .build()
        );
    }

    @PostMapping("/restore/{playgroundId}")
    public ResponseEntity<ResponsePayload<PlaygroundDto, ?>> restorePlayground(@PathVariable("playgroundId") String playgroundId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponsePayload.<PlaygroundDto, Void>builder()
                        .data(playgroundService.restorePlayground(playgroundId))
                        .build()
        );
    }
}
