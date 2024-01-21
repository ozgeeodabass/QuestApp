package com.project.questapp.business.abstracts;

import com.project.questapp.entities.Comment;
import com.project.questapp.entities.User;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> findAll(Optional<Long> postId, Optional<Long> userId);
    List<Comment> findAllByPostId(Long postId);
    List<Comment> findAllByUserId(Long userId);

    Comment add(Comment comment);

    void delete(long id);

    Comment update(long id,Comment comment);

    Comment findById(long id);
}
