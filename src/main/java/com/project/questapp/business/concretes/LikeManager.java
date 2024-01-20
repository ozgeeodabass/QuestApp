package com.project.questapp.business.concretes;

import com.project.questapp.business.abstracts.LikeService;
import com.project.questapp.dataAccess.LikeRepository;
import com.project.questapp.entities.Like;
import com.project.questapp.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class LikeManager implements LikeService {

    private LikeRepository repository;

    @Autowired
    public LikeManager(LikeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Like> findAllLikes(Optional<Long> userId, Optional<Long> postId) {
        if(userId.isPresent())
            return repository.findAllByUserId(userId.get());
        else if(postId.isPresent())
            return repository.findAllByPostId(postId.get());
        else if(postId.isPresent()&&userId.isPresent())
            return repository.findAllByUserIdAndPostId(userId.get(),postId.get());
        else
            return repository.findAll();

    }

    @Override
    public List<Like> findAllByUserId(Long userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public List<Like> findAllByPostId(Long postId) {
        return repository.findAllByPostId(postId);
    }

    @Override
    public void add(Like like) {
        repository.save(like);
    }

    @Override
    public void delete(long id) {
        Like likeFound = repository.findById(id).get();
        repository.delete(likeFound);
    }

    @Override
    public Like getByLikeId(long id) {
        return repository.findById(id).orElse(null);
    }
}
