package com.project.questapp.business.concretes;

import com.project.questapp.business.abstracts.LikeService;
import com.project.questapp.business.abstracts.PostService;
import com.project.questapp.business.abstracts.UserService;
import com.project.questapp.dataAccess.LikeRepository;
import com.project.questapp.entities.Comment;
import com.project.questapp.entities.Like;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.reponses.LikeResponse;
import com.project.questapp.reponses.PostResponse;
import com.project.questapp.requests.LikeCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeManager implements LikeService {

    private LikeRepository repository;
    private UserService userService;
    private PostService postService;

    @Autowired
    public LikeManager(LikeRepository repository,UserService userService, PostService postService) {
        this.repository = repository;
        this.userService=userService;
        this.postService=postService;
    }

    @Override
    public List<LikeResponse> findAllLikes(Optional<Long> userId, Optional<Long> postId) {
        List<Like> list;
        if(userId.isPresent() && postId.isPresent()) {
            list = repository.findAllByUserIdAndPostId(userId.get(), postId.get());
        }else if(userId.isPresent()) {
            list = repository.findAllByUserId(userId.get());
        }else if(postId.isPresent()) {
            list = repository.findAllByPostId(postId.get());
        }else
            list = repository.findAll();
        return list.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());
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
    public Like add(LikeCreateRequest request) {
        User user = userService.getByUserId(request.getUserId());
        Post post = postService.getByPostId(request.getPostId());
        if(user != null && post != null) {
            Like likeToSave = new Like();
            likeToSave.setId(request.getId());
            likeToSave.setPost(post);
            likeToSave.setUser(user);
            return repository.save(likeToSave);
        }else
            return null;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Like getByLikeId(Long id) {
        return repository.findById(id).orElse(null);
    }
}
