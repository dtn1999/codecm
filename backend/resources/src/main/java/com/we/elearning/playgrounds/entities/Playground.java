package com.we.elearning.playgrounds.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Table(name = "PLAYGROUNDS")
@Entity
@EntityListeners(AuditingEntityListener.class)
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
    private String imageUrl;

    // (Service) cross reference
    @Column(unique = true)
    private Long workspaceId;
    @NotNull
    private String userId;
    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;
    @Column(name = "last_modified_date")
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
