package com.we.elearning.security;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

public class Auth0UserInfoService {
    private final WebClient auth0WebClient;

    public Auth0UserInfoService(WebClient auth0WebClient) {
        this.auth0WebClient = auth0WebClient;
    }

    @SneakyThrows
    public Auth0UserInfoDto getAuth0UserInfo(String accessToken) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return auth0WebClient.get()
                .uri("")
                .header("Authorization", String.format("Bearer %s", accessToken))
                .retrieve()
                .toEntity(Auth0UserInfoDto.class)
                .toFuture()
                .get()
                .getBody()
                ;
    }
}
