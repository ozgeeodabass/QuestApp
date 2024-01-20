package com.project.questapp.api.controllers;

import com.project.questapp.business.abstracts.LikeService;
import com.project.questapp.entities.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/likes")
public class LikeControllers {

    private LikeService service;

    @Autowired
    public LikeControllers(LikeService service) {
        this.service = service;
    }

    @GetMapping("/findall")
    List<Like> findAllLikes(Optional<Long> userId, Optional<Long> postId){
        return this.service.findAllLikes(userId,postId);
    }

    @GetMapping("/findallbyuserid/{id}")
    List<Like> findAllByUserId(@PathVariable Long userId){
        return this.service.findAllByUserId(userId);
    }
    @GetMapping("/findallbypostid/{id}")
    List<Like> findAllByPostId(@PathVariable Long postId){
        return this.service.findAllByPostId(postId);
    }

    @PostMapping("/create")
    void add(@RequestBody Like like){
        this.service.add(like);
    }

    @DeleteMapping("/delete")
    void delete(@PathVariable long id){
        this.delete(id);
    }

    @GetMapping("/getByLikeId/{id}")
    Like getByLikeId(@PathVariable long id){
        return this.service.getByLikeId(id);
    }
}
