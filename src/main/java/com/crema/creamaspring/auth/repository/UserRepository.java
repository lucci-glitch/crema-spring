package com.crema.creamaspring.auth.repository;

import com.crema.creamaspring.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}