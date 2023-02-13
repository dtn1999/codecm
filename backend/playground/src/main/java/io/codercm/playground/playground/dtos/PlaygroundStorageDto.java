package io.codercm.playground.playground.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class PlaygroundStorageDto {
    private UUID id;
    private String volumeId;
    private String volumeName;
    private int size;
}
