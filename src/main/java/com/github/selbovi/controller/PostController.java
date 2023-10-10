package com.github.selbovi.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.github.selbovi.domain.Post;
import com.github.selbovi.dto.PostDto;
import com.github.selbovi.mapper.PostMapper;
import com.github.selbovi.repo.PostRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @RequestMapping("/posts")
    public ResponseEntity<List<PostDto>> getPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostDto> result = posts.stream().map(PostMapper.INSTANCE::postToPostDto).collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
