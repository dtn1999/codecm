package com.we.elearning.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ResourcesApplicationSecurityConfig {
    @Value("${we.elearning.security.auth0.user-info-url}")
    private String introspectionUri;

    @Bean
    public SecurityWebFilterChain configure(ServerHttpSecurity httpSecurity) {
        httpSecurity
                .cors()
                .and()
                .csrf()
                    .disable()
                .authorizeExchange()
                    .pathMatchers("/actuator/**", "/api/v1/templates")
                            .permitAll()
                    .anyExchange()
                        .authenticated()
                .and()
                .oauth2ResourceServer(oauth2 ->
                        oauth2.opaqueToken( opaqueTokenSpec ->
                                opaqueTokenSpec.introspector(
                                        new UserInfoOpaqueTokenIntrospect(auth0UserInfoService())))
                )
                ;
        return httpSecurity.build();
    }

    @Bean
    public Auth0UserInfoServiceClient auth0UserInfoService() {
        return HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(WebClient.create(introspectionUri)))
                .build()
                .createClient(Auth0UserInfoServiceClient.class);
    }
}
