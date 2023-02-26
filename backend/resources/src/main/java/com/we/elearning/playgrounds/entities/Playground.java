package com.we.elearning.playgrounds.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "PLAYGROUNDS")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Playground {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String githubRepoUrl;
    private String description;
    private String instanceUrl;
    @Column(unique = true)
    private Long workspaceId;
}
