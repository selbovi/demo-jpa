package com.github.selbovi.dbinit;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.github.selbovi.domain.Comment;
import com.github.selbovi.domain.Post;
import com.github.selbovi.domain.PostMetadata;
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

    @Override
    public void afterPropertiesSet() {
        ZonedDateTime start = ZonedDateTime.now();
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
            var postMetadata = new PostMetadata();
            postMetadata.setPublishDate(start.minus(value, ChronoUnit.DAYS));
            post.setMetadata(postMetadata);
            postMetadata.setPost(post);
            postRepository.save(post);
        });
    }

}
