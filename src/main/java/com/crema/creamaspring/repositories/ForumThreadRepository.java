package com.crema.creamaspring.repositories;

import com.crema.creamaspring.models.ForumThread;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForumThreadRepository extends JpaRepository<ForumThread, Integer> {
    List<ForumThread> findForumThreadById(String id);
    List<ForumThread> findAll();
}
