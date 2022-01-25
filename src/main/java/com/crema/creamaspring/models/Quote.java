package com.crema.creamaspring.models;

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

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "post_id", nullable = false)
//    @JsonIgnore
//    private Post post;

    private EQouteCategory category;

    public Quote(String text, EQouteCategory category) {
        this.text = text;
        this.category = category;
    }

    public Quote() {}
}
