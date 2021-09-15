package com.beekei.springsecurityjwt.user.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserJoinDTO {
    private String loginid;
    private String password;
    private String email;
    private String name;
}
