package io.codercm.playground.playground.dtos;

import io.codercm.playground.playground.entities.PlaygroundStatus;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PlaygroundDto {
    private String name;
    private String description;
    private String runnerId;
    private String volumeId;
    private String instanceUrl;
    private PlaygroundStatus status;
}
