package com.crema.creamaspring.auth.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles",fetch= FetchType.EAGER)
    private Set<User> users;

    public Role(String name, HashSet<User> users) {
        this.name = name;
        this.users = users;
    }

    public Role() {

    }

    public String getName() {
        return name;
    }
}
