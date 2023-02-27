package com.we.elearning.config;

import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.proc.DefaultJOSEObjectTypeVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
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
    public ReactiveJwtDecoder jwtDecoder(@Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}") final String issuer,
                                         @Value("${we.elearning.security.auth0.audience}") final String audience) {
        NimbusReactiveJwtDecoder jwtDecoder = (NimbusReactiveJwtDecoder)ReactiveJwtDecoders.fromOidcIssuerLocation(issuer);
        OAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<Jwt>(
                new AudienceValidator(audience),
                new JwtIssuerValidator(issuer)
        );
        jwtDecoder.setJwtValidator(validator);
        return jwtDecoder;
    }
}
