package io.codercm.playground.providers.linode.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class LinodeConfig {

    @Value("${playground.provider.linode.base-url}")
    private String linodeBaseUri;
    @Value("${playground.provider.linode.access-token:-}")
    private String linodeAccessToken;
    @Bean(name = "linodeWebClient")
    public WebClient linodeWebClient() {
        return WebClient.builder()
                .baseUrl(linodeBaseUri)
                .defaultHeaders(headers -> headers.add("Authorization", String.format("Bearer %s", linodeAccessToken)))
                .build();
    }
}
