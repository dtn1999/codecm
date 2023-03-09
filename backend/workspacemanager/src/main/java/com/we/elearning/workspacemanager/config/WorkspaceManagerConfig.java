package com.we.elearning.workspacemanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableJpaRepositories(basePackages = "com.we.elearning.workspacemanager.repositories")
@EnableJpaAuditing
@EnableWebFlux
@Configuration
public class WorkspaceManagerConfig {
}
