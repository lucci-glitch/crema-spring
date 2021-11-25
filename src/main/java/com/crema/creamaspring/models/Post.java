package com.crema.creamaspring.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Post {

    @Id
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ForumThread forumThread;

    public Post(int id, ForumThread forumThread) {
        this.id = id;
        this.forumThread = forumThread;
    }

    public Post() {}
}
