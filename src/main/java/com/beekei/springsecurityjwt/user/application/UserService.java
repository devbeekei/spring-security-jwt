package com.beekei.springsecurityjwt.user.application;

import com.beekei.springsecurityjwt.user.application.dto.UserInfoDTO;
import com.beekei.springsecurityjwt.user.application.dto.UserJoinDTO;
import com.beekei.springsecurityjwt.user.domain.User;
import com.beekei.springsecurityjwt.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public void join(UserJoinDTO userJoinDTO) {
        User alreadyUser = userRepository.findFirstUserByLoginidOrderByIdAsc(userJoinDTO.getLoginid()).orElse(null);
        if (alreadyUser != null) throw new RuntimeException("이미 등록된 아이디 입니다.");

        User saveUser = User.builder()
                .loginid(userJoinDTO.getLoginid())
                .password(passwordEncoder.encode(userJoinDTO.getPassword()))
                .email(userJoinDTO.getEmail())
                .name(userJoinDTO.getName())
                .build();

        userRepository.save(saveUser);
    }

    @Transactional(readOnly = true)
    public UserInfoDTO getInfo(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("회원 정보가 존재하지 않습니다."));
        return UserInfoDTO.builder()
                .id(user.getId())
                .userid(user.getLoginid())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

}
