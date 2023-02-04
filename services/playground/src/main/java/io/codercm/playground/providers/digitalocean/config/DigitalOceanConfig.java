package io.codercm.playground.providers.digitalocean.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class DigitalOceanConfig {
    @Value("${playground.digitalocean.base-url}")
    private String digitalOceanBaseUri;
    @Value("${playground.digitalocean.access-token:-}")
    private String digitalOceanAccessToken;

    @Bean(name = "digitalOceanWebClient")
    public WebClient linodeWebClient() {
        return WebClient.builder()
                .baseUrl(digitalOceanBaseUri)
                .defaultHeaders(headers -> headers.add("Authorization",
                        String.format("Bearer %s", digitalOceanAccessToken)))
                .build();
    }
}
