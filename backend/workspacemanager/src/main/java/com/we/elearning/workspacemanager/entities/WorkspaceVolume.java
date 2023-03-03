package com.we.elearning.workspacemanager.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Transient
    private String mountPath;
    private int size;

    // Foreign Entity
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "workspaceVolume")
    private Workspace workspace;
}
