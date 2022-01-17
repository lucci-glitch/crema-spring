package com.crema.creamaspring.auth.service;

import com.crema.creamaspring.auth.model.Role;
import com.crema.creamaspring.auth.model.User;

import java.util.Set;

public interface UserService {
    void save(User user);
    void addRolesToUser(User user, Set<Role> roles);
    User findByUsername(String username);
}