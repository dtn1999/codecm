package com.we.elearning.config;

import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.proc.DefaultJOSEObjectTypeVerifier;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class ResourcesApplicationSecurityConfig {
    @Bean
    public SecurityWebFilterChain configure(ServerHttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors()
                .and()
                .csrf()
                    .disable()
                .authorizeExchange()
                    .pathMatchers("/actuator/**")
                            .permitAll()
                    .anyExchange()
                        .authenticated()
                .and()
                .oauth2ResourceServer()
                    .jwt()
                ;
        return httpSecurity.build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder(OAuth2ResourceServerProperties properties) {
        return NimbusReactiveJwtDecoder
                .withJwkSetUri(properties.getJwt().getJwkSetUri())
                .jwtProcessorCustomizer(customizer -> {
                    customizer
                            .setJWSTypeVerifier(
                                    new DefaultJOSEObjectTypeVerifier<>(new JOSEObjectType("at+jwt"))
                            );
                })
                .build()
                ;
    }
}
