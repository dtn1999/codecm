package com.we.elearning.workspacemanager.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Table(name = "WORKSPACES")
@Entity
@EntityListeners(AuditingEntityListener.class)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Workspace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String runnerId;
    private String host;
    private int port;
    @Enumerated(EnumType.STRING)
    private WorkspaceStatus status;

    // Foreign Entity
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "workspace_volume_id", referencedColumnName = "id", nullable = false)
    private WorkspaceVolume workspaceVolume;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;
    @Column(name = "last_modified_date")
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
