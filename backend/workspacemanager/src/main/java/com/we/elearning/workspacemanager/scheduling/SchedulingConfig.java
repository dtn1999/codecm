package com.we.elearning.workspacemanager.scheduling;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "we.elearning.scheduler.enabled", matchIfMissing = true)
public class SchedulingConfig {
}
