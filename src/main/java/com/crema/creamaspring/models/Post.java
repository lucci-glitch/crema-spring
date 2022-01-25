package com.crema.creamaspring.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Post {

    @Id
    private String id;
//    @Lob
//    private String text;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "forum_thread_id", nullable = false)
    @JsonIgnore
    private ForumThread forumThread;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private List<Quote> quotes;

    public Post(String id, ForumThread forumThread, List<Quote> quotes) {
        this.id = id;
        this.forumThread = forumThread;
        this.quotes = quotes;
    }

    public Post() {}

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", forumThread=" + forumThread +
                ", quotes=" + quotes +
                '}';
    }
}


