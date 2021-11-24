package com.crema.creamaspring.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "forumThreads")
public class ForumThread {
    @Id
    private String id;
    private String text;

    @OneToMany(mappedBy = "post")
    private List<Post> posts;

    public ForumThread(){};

    public ForumThread(String id, String text) {
        this.id = id;
        this.text = text;
    }
}
