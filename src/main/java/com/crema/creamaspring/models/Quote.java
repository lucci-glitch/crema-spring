package com.crema.creamaspring.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Quote {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public Quote(String text, Post post) {
        this.text = text;
        this.post = post;
    }

    public Quote() {}
}
