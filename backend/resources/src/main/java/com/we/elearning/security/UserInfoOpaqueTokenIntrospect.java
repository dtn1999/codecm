package com.we.elearning.security;

import lombok.SneakyThrows;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.ReactiveOpaqueTokenIntrospector;
import reactor.core.publisher.Mono;

public class UserInfoOpaqueTokenIntrospect implements ReactiveOpaqueTokenIntrospector {
    private final Auth0UserInfoService auth0UserInfoService;

    public UserInfoOpaqueTokenIntrospect(Auth0UserInfoService auth0UserInfoService) {
        this.auth0UserInfoService = auth0UserInfoService;
    }

    @Override
    @SneakyThrows
    public Mono<OAuth2AuthenticatedPrincipal> introspect(String token) {
        return auth0UserInfoService.getAuth0UserInfo(String.format("Bearer %s", token))
                .map(OAuth2AuthenticatedPrincipalImpl::new);
    }
}
