package com.project.questapp.business.concretes;

import com.project.questapp.business.abstracts.LikeService;
import com.project.questapp.business.abstracts.PostService;
import com.project.questapp.business.abstracts.UserService;
import com.project.questapp.dataAccess.PostRepository;
import com.project.questapp.entities.Like;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.reponses.LikeResponse;
import com.project.questapp.reponses.PostResponse;
import com.project.questapp.requests.PostCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostManager implements PostService {

    private PostRepository repository;
    private UserService userService;
    private LikeService likeService;

    @Autowired
    public PostManager(PostRepository repository,UserService userService) {

        this.repository = repository;
        this.userService=userService;
    }

    public void setLikeService(LikeService likeService){
        this.likeService=likeService;
    }

    @Override
    public List<PostResponse> findAllPosts(Optional<Long> userId) {
        List<Post> list;
        if(userId.isPresent())
            list= repository.findAllByUserId(userId.get());
        list= repository.findAll();
        return list.stream().map(p->{
            List<LikeResponse> likes = likeService.findAllLikes(null,Optional.of(p.getId()));
            return new PostResponse(p,likes);}).collect(Collectors.toList());
    }

    @Override
    public List<PostResponse> findAllByUserId(Long userId) {
        List<Post> list = repository.findAllByUserId(userId);
        return list.stream().map(p->{
            List<LikeResponse> likes = likeService.findAllLikes(null,Optional.of(p.getId()));
            return new PostResponse(p,likes);}).collect(Collectors.toList());
    }

    @Override
    public List<PostResponse> findAllByTitleContaining(String text) {
        List<Post> list = repository.findAllByTitleContaining(text);
        return list.stream().map(p->{
            List<LikeResponse> likes = likeService.findAllLikes(null,Optional.of(p.getId()));
            return new PostResponse(p,likes);}).collect(Collectors.toList());
    }

    @Override
    public List<PostResponse> findAllByTitle(String title) {
        List<Post> list =repository.findAllByTitle(title);
        return list.stream().map(p->{
            List<LikeResponse> likes = likeService.findAllLikes(null,Optional.of(p.getId()));
            return new PostResponse(p,likes);}).collect(Collectors.toList());
    }

    @Override
    public Post add(PostCreateRequest post) {
        User user = userService.getByUserId(post.getUserId());
        if(user==null)
            return null;
        Post toSave = new Post();
        toSave.setId(post.getId());
        toSave.setText(post.getText());
        toSave.setTitle(post.getTitle());
        toSave.setUser(user);
        return repository.save(toSave);

    }

    @Override
    public void delete(long id) {
        Post postFound = repository.findById(id).get();
        repository.delete(postFound);
    }

    @Override
    public Post update(long id, Post post) {
        Optional<Post> postFoundOptional = repository.findById(id);
        Post postFound = postFoundOptional.get();
        if(postFound!=null&&postFound.getId()==id){
            postFound.setText(post.getText());
            postFound.setTitle(post.getTitle());
            repository.save(postFound);
            return postFound;
        }else
            return null;
    }

    @Override
    public Post getByPostId(long id) {
        return repository.findById(id).orElse(null);
    }
}
