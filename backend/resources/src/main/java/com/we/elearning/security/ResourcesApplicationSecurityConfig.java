package com.we.elearning.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.introspection.ReactiveOpaqueTokenIntrospector;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ResourcesApplicationSecurityConfig {
    @Bean
    public SecurityWebFilterChain configure(ServerHttpSecurity httpSecurity,
                                            @Value("${we.elearning.security.auth0.user-info-url}") final String introspectionUri) throws Exception {
        httpSecurity
                .cors()
                .and()
                .csrf()
                    .disable()
                .authorizeExchange()
                    .pathMatchers("/actuator/**", "/api/v1/resources/templates")
                            .permitAll()
                    .anyExchange()
                        .authenticated()
                .and()
                .oauth2ResourceServer(oauth2 ->
                        oauth2.opaqueToken( opaqueTokenSpec ->
                                opaqueTokenSpec.introspector(
                                        new UserInfoOpaqueTokenIntrospect(auth0UserInfoService(introspectionUri))))
                )
                ;
        return httpSecurity.build();
    }

    private Auth0UserInfoService auth0UserInfoService(String auth0UserInfoUrl) {
        return new Auth0UserInfoService(WebClient.create(auth0UserInfoUrl));
    }
}
