package io.codercm.playground.playground.controllers;

import io.codercm.playground.common.entities.ResponsePayload;
import io.codercm.playground.playground.dtos.PlaygroundDto;
import io.codercm.playground.playground.services.PlaygroundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/v1/playgrounds")
@RequiredArgsConstructor
public class PlaygroundController {
   private final PlaygroundService playgroundService;

   @GetMapping
   public ResponseEntity<ResponsePayload<List<PlaygroundDto>,?>> getAllPlaygrounds() {
      return null;
   }

   @PostMapping
   public ResponseEntity<ResponsePayload<PlaygroundDto,?>> createPlayground() {
      return null;
   }
}
