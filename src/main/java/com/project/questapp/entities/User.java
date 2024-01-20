package com.project.questapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.questapp.core.entities.IEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String userName;
    String password;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Comment> comments;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Like> likes;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Post> posts;



}
