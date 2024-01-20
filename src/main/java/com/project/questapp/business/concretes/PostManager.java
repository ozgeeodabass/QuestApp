package com.project.questapp.business.concretes;

import com.project.questapp.business.abstracts.PostService;
import com.project.questapp.dataAccess.PostRepository;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostManager implements PostService {

    private PostRepository repository;

    @Autowired
    public PostManager(PostRepository repository) {
        this.repository = repository;
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
    public void add(Post post) {
        repository.save(post);
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
