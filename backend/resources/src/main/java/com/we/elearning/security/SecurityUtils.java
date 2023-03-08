package com.we.elearning.security;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class SecurityUtils {

    public Auth0UserInfoDto getAuthenticatedUser() {
        OAuth2AuthenticatedPrincipalImpl principal = (OAuth2AuthenticatedPrincipalImpl) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return principal.getAuth0UserInfoDto();
    }
}
