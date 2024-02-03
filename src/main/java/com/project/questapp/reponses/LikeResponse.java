package com.project.questapp.reponses;

import com.project.questapp.entities.Like;
import com.project.questapp.entities.Post;
import lombok.Data;

import java.util.List;

@Data
public class LikeResponse {

    Long id;
    Long userId;
    Long postId;

    public LikeResponse(Like entity){
        this.id= entity.getId();
        this.userId=entity.getUser().getId();
        this.postId = entity.getPost().getId();
    }
}
