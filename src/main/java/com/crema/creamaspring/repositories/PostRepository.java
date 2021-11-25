package com.crema.creamaspring.repositories;

import com.crema.creamaspring.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
