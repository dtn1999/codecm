package com.we.elearning.workspacemanager.dots;

import com.we.elearning.workspacemanager.entities.Workspace;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkspaceVolumeDto {
    private Long id;
    private String name;
    private int size;
}
