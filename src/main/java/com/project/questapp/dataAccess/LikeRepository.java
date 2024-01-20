package com.project.questapp.dataAccess;

import com.project.questapp.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like,Long> {
    List<Like> findAllByPostId(Long postId);
    List<Like> findAllByUserId(Long userId);
    List<Like> findAllByUserIdAndPostId(Long userId, Long postId);
}
