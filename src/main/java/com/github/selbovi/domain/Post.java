package com.github.selbovi.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

@Entity
@Setter
@Getter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String content;

    @BatchSize(size = 100)
    @OneToMany(cascade = CascadeType.ALL)
    Set<Comment> comments = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "post")
    PostMetadata metadata;
}
