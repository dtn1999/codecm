package io.codercm.playground.playground.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Playground {
    @Id
    private UUID id;
    private String name;
    private String description;
    private String runnerId;
    private String volumeId;
    private String instanceUrl;
    private PlaygroundStatus status;
}
