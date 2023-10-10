package com.github.selbovi.dbinit;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.github.selbovi.domain.Comment;
import com.github.selbovi.domain.Post;
import com.github.selbovi.repo.CommentRepository;
import com.github.selbovi.repo.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommentsFiller implements InitializingBean {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        IntStream.range(0, 100).forEach(value -> {
            var post = new Post();
            post.setContent("post_content_" + value);

            Set<Comment> comments = IntStream.range(0, 100).mapToObj(operand -> {
                var comment = new Comment();
                comment.setCommentText(operand + "_comment_for_post_" + value);
                comment.setPost(post);
                return comment;
            }).collect(Collectors.toSet());

            post.getComments().addAll(comments);

            postRepository.save(post);
        });
    }

}
