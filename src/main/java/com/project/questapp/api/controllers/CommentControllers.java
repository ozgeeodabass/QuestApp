package com.project.questapp.api.controllers;

import com.project.questapp.business.abstracts.CommentService;
import com.project.questapp.entities.Comment;
import com.project.questapp.requests.CommentCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentControllers {

    private CommentService service;

    @Autowired
    public CommentControllers(CommentService service) {
        this.service = service;
    }

    @GetMapping("/findall")
    List<Comment> findAll(@RequestParam Optional<Long> postId,@RequestParam Optional<Long> userId){
        return this.service.findAll(userId,postId);
    }


    @PostMapping("/create")
    Comment add(@RequestBody CommentCreateRequest comment){
        return this.service.add(comment);
    }

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable long id){
        this.service.delete(id);
    }

    @PutMapping("/update/{id}")
    Comment update(@PathVariable long id,@RequestBody Comment comment){
        return this.service.update(id,comment);
    }

    @GetMapping("/findById/{id}")
    Comment findById(@PathVariable long id){
        return this.service.findById(id);
    }
}
