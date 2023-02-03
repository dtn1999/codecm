package io.codercm.playground.playground.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity(name = "playground")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Playground {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @Column(name= "name")
    private String name;
    @Column(name= "description")
    private String description;
    /**
     * The Runner Id in this case represents the Id of the platform where the playground is running. (container, vm, etc)
     */
    @Column(name= "runner_id")
    private String runnerId;

    @Column(name= "host")
    private String host;
    @Column(name= "port")
    private int port;
    /**
     * The Playground App Port is the port where the playground app is running. Inside the container.
     */
    @Column(name= "playground_app_port")
    private int playgroundAppPort;
    @Column(name= "status")
    @Enumerated(EnumType.STRING)
    private PlaygroundStatus status;

    // Relationships
    @OneToOne(mappedBy = "playground")
    private PlaygroundStorage storage;
}
