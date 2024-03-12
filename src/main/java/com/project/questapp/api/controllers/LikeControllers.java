package com.project.questapp.api.controllers;

import com.project.questapp.business.abstracts.LikeService;
import com.project.questapp.entities.Like;
import com.project.questapp.reponses.LikeResponse;
import com.project.questapp.requests.LikeCreateRequest;
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
    public List<LikeResponse> findAllLikes(@RequestParam Optional<Long> userId, @RequestParam  Optional<Long> postId){
        return this.service.findAllLikes(userId,postId);
    }
    @PostMapping("/create")
    public Like add(@RequestBody LikeCreateRequest like){
       return this.service.add(like);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        this.service.deleteById(id);
    }

    @GetMapping("/getByLikeId/{id}")
    public Like getByLikeId(@PathVariable long id){
        return this.service.getByLikeId(id);
    }
}
