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
    List<Like> findAllLikes(@RequestParam Optional<Long> userId,@RequestParam  Optional<Long> postId){
        return this.service.findAllLikes(userId,postId);
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
