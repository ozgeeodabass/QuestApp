package com.project.questapp.business.abstracts;

import com.project.questapp.entities.Like;
import com.project.questapp.entities.Post;
import com.project.questapp.reponses.LikeResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeService  {
    List<LikeResponse> findAllLikes(Optional<Long> userId, Optional<Long> postId);
    List<LikeResponse> findAllByUserId(Long userId);
    List<LikeResponse> findAllByPostId(Long postId);
    void add(Like like);

    void delete(long id);

    Like getByLikeId(long id);
}
