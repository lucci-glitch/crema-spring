package com.crema.creamaspring.repositories;

import com.crema.creamaspring.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findPostById(String id);
    List<Post> findAll();
}