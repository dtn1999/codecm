package com.we.elearning.workspacemanager.services.providers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateWorkspaceRequest {
    private int codeServerPort;
    private String volumeName;
    private String githubRepoUrl;
}
