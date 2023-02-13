package io.codercm.playground.providers.entities;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class LocationResponse {
    private String host;
    private int port;
}
