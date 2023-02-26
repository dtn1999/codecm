package com.we.elearning.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ResourcesBeanFactory {

    @Bean(name = "workspaceManagerWebClient")
    public WebClient webClient(@Value("${we.elearning.workspace-manager.base-url}") String workspaceManagerBaseUrl) {
        return WebClient.create(workspaceManagerBaseUrl);
    }
}
