package com.we.elearning.workspacemanager.dots;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkspaceVolumeDto {
    private Long id;
    private String name;
    private int size;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
