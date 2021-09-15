package com.beekei.springsecurityjwt.user.infra;

import com.beekei.springsecurityjwt.user.domain.User;
import com.beekei.springsecurityjwt.user.domain.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long>, UserRepository {
}
