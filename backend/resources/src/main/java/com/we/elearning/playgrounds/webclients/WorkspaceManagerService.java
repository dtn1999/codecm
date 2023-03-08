package com.we.elearning.playgrounds.webclients;

import com.we.elearning.common.dtos.ApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public interface WorkspaceManagerService {
    @PostExchange("api/v1/workspaces")
    Mono<ApiResponse> createWorkspace(@RequestBody Map<String, Object> request);
    @DeleteExchange("api/v1/workspaces/{workspaceId}")
    Mono<ApiResponse> deleteWorkspace(@PathVariable("workspaceId") final Long workspaceId);
    @PostExchange("api/v1/workspaces/{workspaceId}/restore")
    Mono<ApiResponse> restoreWorkspace(@PathVariable("workspaceId") final Long workspaceId);
}
