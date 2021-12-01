package com.crema.creamaspring.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Post {

    @Id
    private String id;
    @Lob
    private String text;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "forum_thread_id", nullable = false)
    @JsonIgnore
    private ForumThread forumThread;

    public Post(String id, ForumThread forumThread,String text) {
        this.id = id;
        this.forumThread = forumThread;
        this.text = text;
    }

    public Post() {}

}
