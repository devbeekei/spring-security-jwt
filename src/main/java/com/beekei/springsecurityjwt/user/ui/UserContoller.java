package com.beekei.springsecurityjwt.user.ui;

import com.beekei.springsecurityjwt.common.ApiResponse;
import com.beekei.springsecurityjwt.common.DataApiResponse;
import com.beekei.springsecurityjwt.user.application.UserService;
import com.beekei.springsecurityjwt.user.application.dto.UserInfoDTO;
import com.beekei.springsecurityjwt.user.application.dto.UserJoinDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class UserContoller {

    private final UserService userService;

    @PostMapping(name = "회원가입", value = "/user")
    public ApiResponse join(@RequestBody UserJoinDTO userJoinDTO) {
        userService.join(userJoinDTO);
        return new ApiResponse();
    }

    @GetMapping(name = "회원 정보조회", value = "/user/{id}")
    public DataApiResponse<UserInfoDTO> info(@PathVariable long id) {
        UserInfoDTO userInfoDTO = userService.getInfo(id);
        return new DataApiResponse<>(userInfoDTO);
    }

}
