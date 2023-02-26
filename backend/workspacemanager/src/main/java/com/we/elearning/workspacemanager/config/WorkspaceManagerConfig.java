package com.we.elearning.workspacemanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.we.elearning.workspacemanager.repositories")
@Configuration
public class WorkspaceManagerConfig {
}
