package com.project.questapp.dataAccess;

import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByUserId(Long userId);
    List<Post> findAllByTitleContaining(String text);
    List<Post> findAllByTitle(String title);

}
