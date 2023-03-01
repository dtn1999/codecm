package com.we.elearning.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auth0UserInfoDto {
    private String sub;
    private String nickname;
    private String name;
    private String picture;
    private String updated_at;
    private String email;
    private boolean email_verified;
}
