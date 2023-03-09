package com.we.elearning.playgrounds.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaygroundDto {
    private Long id;
    private String name;
    private String githubRepoUrl;
    private String description;
    private String instanceUrl;
    private String imageUrl;
    private Long workspaceId;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
