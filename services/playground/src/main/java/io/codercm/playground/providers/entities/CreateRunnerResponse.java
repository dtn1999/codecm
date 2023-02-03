package io.codercm.playground.providers.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
public class CreateRunnerResponse extends LocationResponse {
    private String runnerId;
}
