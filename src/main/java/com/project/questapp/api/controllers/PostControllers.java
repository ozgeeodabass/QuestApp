package com.project.questapp.api.controllers;

import com.project.questapp.business.abstracts.PostService;
import com.project.questapp.entities.Post;
import com.project.questapp.reponses.PostResponse;
import com.project.questapp.requests.PostCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostControllers {

    private PostService service;

    @Autowired
    public PostControllers(PostService service) {
        this.service = service;
    }

    @GetMapping("/getall")
    public List<PostResponse> findAllPosts(@RequestParam Optional<Long> userId) {
      return this.service.findAllPosts(userId);
    }

    @GetMapping("/getallbytitlecontaining/{text}")
    public List<PostResponse> findAllByTitleContaining(@PathVariable String text) {
        return this.service.findAllByTitleContaining(text);
    }

    @GetMapping("/getallbytitle/{title}")
    public List<PostResponse> findAllByTitle(@PathVariable String title) {

        return this.service.findAllByTitle(title);
    }

    @PostMapping("/create")
    public Post add(@RequestBody PostCreateRequest postRequest) {
        return this.service.add(postRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id) {
        this.service.delete(id);
    }

    @PutMapping("/update/{id}")
    public Post update(@PathVariable long id,@RequestBody Post post) {
      return this.service.update(id,post);
    }

    @GetMapping("/getbypostid/{id}")
    public Post getByPostId(@PathVariable long id) {
        return this.service.getByPostId(id);
    }
}
