package io.codercm.playground.playground.entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PlaygroundPortConfig {
    private int codeServerPort;
    private int playgroundAppPort;
}
