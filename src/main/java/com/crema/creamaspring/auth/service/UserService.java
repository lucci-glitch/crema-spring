package com.crema.creamaspring.auth.service;

import com.crema.creamaspring.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}