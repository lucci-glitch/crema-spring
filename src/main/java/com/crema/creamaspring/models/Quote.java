package com.crema.creamaspring.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Quote {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Lob
    private String text;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    @JsonIgnore
    private Post post;

    private String statement;

    public Quote(String text, Post post,String statement) {
        this.text = text;
        this.post = post;
        this.statement = statement;
    }

    public Quote() {}
}
