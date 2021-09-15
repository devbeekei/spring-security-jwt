package com.beekei.springsecurityjwt.security;

import com.beekei.springsecurityjwt.user.domain.User;
import com.beekei.springsecurityjwt.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findFirstUserByLoginidOrderByIdAsc(username).orElseThrow(() -> new RuntimeException("Not Found User"));
        return new UserDetailsImpl(
                user.getLoginid(),
                user.getPassword(),
                user.getEmail(),
                user.getName(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }

}