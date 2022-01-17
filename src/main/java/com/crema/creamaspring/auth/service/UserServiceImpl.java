package com.crema.creamaspring.auth.service;


import com.crema.creamaspring.auth.model.Role;
import com.crema.creamaspring.auth.model.User;
import com.crema.creamaspring.auth.repository.RoleRepository;
import com.crema.creamaspring.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    public void createRoles() {
        if(roleRepository.findAll().size() == 0 ){
        roleRepository.save(new Role("SUPER_ADMIN", new HashSet<>()));
        roleRepository.save(new Role("SUPER_USER", new HashSet<>()));
        }
    }

    @PostConstruct
    public void createAdmin() {
        if (userRepository.findByUsername("superAdmin") == null) {
            userRepository.save(new User("superAdmin", "admin1337", "admin1337"));


            Set<Role> roles = new HashSet<>();

            roles.add(roleRepository.findRoleByName("SUPER_ADMIN"));

            addRolesToUser(userRepository.findByUsername("superAdmin"), roles);
        }
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findRoleByName("SUPER_USER"));
        user.setRoles(roles);
        userRepository.save(user);
    }

    public void addRolesToUser(User user, Set<Role> roles) {
        user.getRoles().addAll(roles);
        userRepository.findByUsername(user.getUsername()).setRoles(user.getRoles());
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);

    }
}