package com.crema.creamaspring.repositories;

import com.crema.creamaspring.models.ForumThread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ForumThreadRepository extends JpaRepository<ForumThread, Integer> {

    @Query(value = "SELECT *\n" +
            "from forum_thread\n" +
            "JOIN post p on forum_thread.id = p.forum_thread_id\n" +
            "JOIN quote q on p.id = q.post_id\n" +
            "WHERE q.post_id = (SELECT quote.post_id FROM quote WHERE quote.id =1)"
        ,nativeQuery = true)


}
