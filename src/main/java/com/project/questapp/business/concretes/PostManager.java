package com.project.questapp.business.concretes;

import com.project.questapp.business.abstracts.PostService;
import com.project.questapp.business.abstracts.UserService;
import com.project.questapp.dataAccess.PostRepository;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.requests.PostCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostManager implements PostService {

    private PostRepository repository;
    private UserService userService;

    @Autowired
    public PostManager(PostRepository repository,UserService userService) {

        this.repository = repository;
        this.userService=userService;
    }

    @Override
    public List<Post> findAllPosts(Optional<Long> userId) {
        if(userId.isPresent())
            return repository.findAllByUserId(userId.get());
        else
            return repository.findAll();
    }

    @Override
    public List<Post> findAllByUserId(Long userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public List<Post> findAllByTitleContaining(String text) {
        return repository.findAllByTitleContaining(text);
    }

    @Override
    public List<Post> findAllByTitle(String title) {
        return repository.findAllByTitle(title);
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
