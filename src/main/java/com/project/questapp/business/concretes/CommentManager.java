package com.project.questapp.business.concretes;

import com.project.questapp.business.abstracts.CommentService;
import com.project.questapp.dataAccess.CommentRepository;
import com.project.questapp.entities.Comment;
import com.project.questapp.entities.Like;
import com.project.questapp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentManager implements CommentService {

    private CommentRepository repository;

    @Autowired
    public CommentManager(CommentRepository repository) {
        this.repository = repository;
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
    public Comment add(Comment comment) {
        return repository.save(comment);
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
