package com.github.selbovi.dto;

import java.util.Set;

import lombok.Data;

@Data
public class PostDto {

    private Long id;
    private String content;
    private Set<CommentDto> comments;
}
