package com.beekei.springsecurityjwt.user.application.dto;

import lombok.*;

@Builder
@Getter
public class UserInfoDTO {
    private long id;
    private String userid;
    private String email;
    private String name;
}
