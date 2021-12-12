package com.crema.creamaspring.auth.repository;

import com.crema.creamaspring.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}