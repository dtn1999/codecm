package com.we.elearning.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Data
public class OAuth2AuthenticatedPrincipalImpl implements OAuth2AuthenticatedPrincipal {
    private final Auth0UserInfoDto auth0UserInfoDto;
    public OAuth2AuthenticatedPrincipalImpl(Auth0UserInfoDto auth0UserInfoDto) {
        this.auth0UserInfoDto = auth0UserInfoDto;
    }
    @Override
    public Map<String, Object> getAttributes() {
        return Map.of(
                "name", auth0UserInfoDto.getName(),
                "email", auth0UserInfoDto.getEmail(),
                "picture", auth0UserInfoDto.getPicture(),
                "sub", auth0UserInfoDto.getSub(),
                "nickname", auth0UserInfoDto.getNickname()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getName() {
        return auth0UserInfoDto.getName();
    }
}
