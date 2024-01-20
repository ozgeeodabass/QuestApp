package com.project.questapp.business.abstracts;

import com.project.questapp.entities.Like;
import com.project.questapp.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeService  {
    List<Like> findAllLikes(Optional<Long> userId, Optional<Long> postId);
    List<Like> findAllByUserId(Long userId);
    List<Like> findAllByPostId(Long postId);
    void add(Like like);

    void delete(long id);

    Like getByLikeId(long id);
}
