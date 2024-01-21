package com.project.questapp.dataAccess;

import com.project.questapp.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByPostId(Long postId);
    List<Comment> findAllByUserId(Long userId);
    List<Comment> findAllByUserIdAndPostId(Long userId, Long postId);
}
