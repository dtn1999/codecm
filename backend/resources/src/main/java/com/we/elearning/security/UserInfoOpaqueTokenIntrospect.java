package com.we.elearning.security;

import lombok.SneakyThrows;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.ReactiveOpaqueTokenIntrospector;
import reactor.core.publisher.Mono;

public class UserInfoOpaqueTokenIntrospect implements ReactiveOpaqueTokenIntrospector {
    private final Auth0UserInfoServiceClient auth0UserInfoServiceClient;

    public UserInfoOpaqueTokenIntrospect(Auth0UserInfoServiceClient auth0UserInfoServiceClient) {
        this.auth0UserInfoServiceClient = auth0UserInfoServiceClient;
    }

    @Override
    @SneakyThrows
    public Mono<OAuth2AuthenticatedPrincipal> introspect(String token) {
        return auth0UserInfoServiceClient.getAuth0UserInfo(String.format("Bearer %s", token))
                .map(OAuth2AuthenticatedPrincipalImpl::new);
    }
}
