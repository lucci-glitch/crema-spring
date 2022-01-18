package com.crema.creamaspring.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class ForumThread {
    @Id
    private String id;
    private String text;
    private int lastPage;

    public ForumThread(){};

    public ForumThread(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public ForumThread(String id, String text, int lastPage) {
        this.id = id;
        this.text = text;
        this.lastPage = lastPage;
    }
}
