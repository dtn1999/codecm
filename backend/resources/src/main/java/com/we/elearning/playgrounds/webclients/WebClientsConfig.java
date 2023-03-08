package com.we.elearning.playgrounds.webclients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientsConfig {

    @Bean
    public WorkspaceManagerService workspaceManagerService(
            @Value("${we.elearning.workspace-manager.base-url}") final String baseUrl) {
        return HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(WebClient.create(baseUrl)))
                .build()
                .createClient(WorkspaceManagerService.class);
    }
}
