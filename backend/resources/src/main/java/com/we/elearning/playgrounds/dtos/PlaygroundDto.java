package com.we.elearning.playgrounds.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
