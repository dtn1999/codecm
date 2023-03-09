package com.we.elearning.workspacemanager.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name = "WORKSPACE_VOLUMES")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkspaceVolume {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    private String mountPath;
    private long size;

    // Foreign Entity
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "workspaceVolume")
    private Workspace workspace;
}
