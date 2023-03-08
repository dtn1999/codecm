package com.we.elearning.security;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

public interface Auth0UserInfoServiceClient {
    @GetExchange("")
    Mono<Auth0UserInfoDto> getAuth0UserInfo(@RequestHeader("Authorization") String token);
}
