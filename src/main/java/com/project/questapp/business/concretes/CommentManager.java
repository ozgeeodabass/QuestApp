package com.project.questapp.business.concretes;

import com.project.questapp.business.abstracts.CommentService;
import com.project.questapp.business.abstracts.PostService;
import com.project.questapp.business.abstracts.UserService;
import com.project.questapp.dataAccess.CommentRepository;
import com.project.questapp.entities.Comment;
import com.project.questapp.entities.Like;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.requests.CommentCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentManager implements CommentService {

    private CommentRepository repository;
    private UserService userService;
    private PostService postService;

    @Autowired
    public CommentManager(CommentRepository repository, UserService userService, PostService postService) {
        this.repository = repository;
        this.userService=userService;
        this.postService=postService;
    }

    @Override
    public List<Comment> findAll(Optional<Long> postId, Optional<Long> userId) {
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
    public List<Comment> findAllByPostId(Long postId) {
        return repository.findAllByPostId(postId);
    }

    @Override
    public List<Comment> findAllByUserId(Long userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public Comment add(CommentCreateRequest commentRequest) {
        User user = userService.getByUserId(commentRequest.getUserId());
        Post post = postService.getByPostId(commentRequest.getPostId());
        if(user!=null&&post!=null){
            Comment toSave = new Comment();
            toSave.setId(commentRequest.getId());
            toSave.setText(commentRequest.getText());
            toSave.setUser(user);
            toSave.setPost(post);
            return repository.save(toSave);
        }else
            return null;
    }

    @Override
    public void delete(long id) {
        Comment commentFound = repository.findById(id).get();
        repository.delete(commentFound);
    }

    @Override
    public Comment update(long id, Comment comment) {
        Optional<Comment> commentOptional = repository.findById(id);
        Comment commentFound = commentOptional.get();
        if(commentFound!=null&&commentFound.getId()==id){
            commentFound.setText(commentFound.getText());
            repository.save(commentFound);
            return commentFound;
        }else
            return null;
    }

    @Override
    public Comment findById(long id) {
        return repository.findById(id).orElse(null);
    }
}
