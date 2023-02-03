package io.codercm.playground.playground.dtos;

import io.codercm.playground.playground.entities.PlaygroundStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Builder
@Data
public class PlaygroundDto {
    private UUID id;
    private String name;
    private String description;
    /**
     * The Runner Id in this case represents the Id of the platform where the playground is running. (container, vm, etc)
     */
    private String runnerId;
    private String volumeId;

    private String host;
    private int port;
    /**
     * The Playground App Port is the port where the playground app is running. Inside the container.
     */
    private int playgroundAppPort;
    private PlaygroundStatus status;
}
