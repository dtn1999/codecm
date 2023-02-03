package io.codercm.playground.playground.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PlaygroundStorageDto {
    private String volumeId;
    private String volumeName;
}
