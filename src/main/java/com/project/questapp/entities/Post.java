package com.project.questapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.questapp.core.entities.IEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Table(name = "posts")
@Data
public class Post implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    User user;

    @Column(name = "title")
    String title;

    @Lob
    @Column(columnDefinition = "text", name = "text")
    String text;
}
