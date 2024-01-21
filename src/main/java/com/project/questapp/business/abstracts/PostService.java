package com.project.questapp.business.abstracts;

import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.requests.PostCreateRequest;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<Post> findAllPosts(Optional<Long> userId);
    List<Post> findAllByUserId(Long userId);
    List<Post> findAllByTitleContaining(String text);
    List<Post> findAllByTitle(String title);
    Post add(PostCreateRequest post);

    void delete(long id);

    Post update(long id,Post post);

    Post getByPostId(long id);
}
