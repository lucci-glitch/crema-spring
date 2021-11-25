package com.crema.creamaspring.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "posts")
public class Post {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "forumThread_id")
    private ForumThread forumThread;

    public Post(int id, ForumThread forumThread) {
        this.id = id;
        this.forumThread = forumThread;
    }

    public Post() {}
}
