package io.codercm.playground.providers.entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateStorageResponse {
    private String volumeId;
    private String volumeName;
    private Integer size;
}
