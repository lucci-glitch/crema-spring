package com.crema.creamaspring.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ForumThread {
    @Id
    private String id;
    private String text;

    public ForumThread(){};

    public ForumThread(String id, String text) {
        this.id = id;
        this.text = text;
    }
}
