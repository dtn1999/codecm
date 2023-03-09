package com.we.elearning.workspacemanager.services.providers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VolumeDetails {
    private long id;
    private String name;
    private String mountPath;
    private long size;
}
