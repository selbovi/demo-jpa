package com.github.selbovi.repo;

import com.github.selbovi.domain.Comment;
import com.github.selbovi.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
