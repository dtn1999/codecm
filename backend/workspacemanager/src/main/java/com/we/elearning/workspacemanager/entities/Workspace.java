package com.we.elearning.workspacemanager.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name = "WORKSPACES")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Workspace {
    @Id
    @GeneratedValue
    private Long id;
    private String runnerId;
    private String host;
    private int port;

    @Enumerated(EnumType.STRING)
    private WorkspaceStatus status;

    // Foreign Entity
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "workspace_volume_id", referencedColumnName = "id")
    private WorkspaceVolume workspaceVolume;
}
