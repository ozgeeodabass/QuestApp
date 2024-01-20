package com.project.questapp.business.abstracts;

import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<Post> findAllPosts(Optional<Long> userId);
    List<Post> findAllByUserId(Long userId);
    List<Post> findAllByTitleContaining(String text);
    List<Post> findAllByTitle(String title);
    void add(Post post);

    void delete(long id);

    Post update(long id,Post post);

    Post getByPostId(long id);
}
