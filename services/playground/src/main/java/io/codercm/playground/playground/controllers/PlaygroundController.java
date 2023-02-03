package io.codercm.playground.playground.controllers;

import io.codercm.playground.common.entities.ResponsePayload;
import io.codercm.playground.playground.dtos.PlaygroundDto;
import io.codercm.playground.playground.services.PlaygroundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/playgrounds")
@RequiredArgsConstructor
public class PlaygroundController {
   private final PlaygroundService playgroundService;

   @GetMapping("/")
   public ResponseEntity<ResponsePayload<List<PlaygroundDto>,?>> getAllPlaygrounds() {
      return null;
   }

   @PostMapping("/")
   public ResponseEntity<ResponsePayload<PlaygroundDto,?>> createPlayground() {
      return ResponseEntity.ok().body(null);
   }
}
