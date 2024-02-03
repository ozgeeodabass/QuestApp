package com.project.questapp.business.concretes;

import com.project.questapp.business.abstracts.LikeService;
import com.project.questapp.dataAccess.LikeRepository;
import com.project.questapp.entities.Like;
import com.project.questapp.entities.Post;
import com.project.questapp.reponses.LikeResponse;
import com.project.questapp.reponses.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeManager implements LikeService {

    private LikeRepository repository;

    @Autowired
    public LikeManager(LikeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<LikeResponse> findAllLikes(Optional<Long> userId, Optional<Long> postId) {
        List<Like> list;
        if(userId.isPresent())
            list= repository.findAllByUserId(userId.get());
        else if(postId.isPresent())
            list= repository.findAllByPostId(postId.get());
        else if(postId.isPresent()&&userId.isPresent())
            list= repository.findAllByUserIdAndPostId(userId.get(),postId.get());
        else
            list= repository.findAll();

        return list.stream().map(l->new LikeResponse(l)).collect(Collectors.toList());
    }

    @Override
    public List<LikeResponse> findAllByUserId(Long userId) {
        List<Like> likes = repository.findAllByUserId(userId);
        return likes.stream().map(l->new LikeResponse(l)).collect(Collectors.toList());
    }

    @Override
    public List<LikeResponse> findAllByPostId(Long postId) {
        List<Like> likes = repository.findAllByPostId(postId);
        return likes.stream().map(l->new LikeResponse(l)).collect(Collectors.toList());
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
