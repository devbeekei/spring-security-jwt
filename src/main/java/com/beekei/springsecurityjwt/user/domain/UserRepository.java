package com.beekei.springsecurityjwt.user.domain;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findFirstUserByLoginidOrderByIdAsc(String username);
    Optional<User> findById(long id);
    User save(User saveUser);
}
