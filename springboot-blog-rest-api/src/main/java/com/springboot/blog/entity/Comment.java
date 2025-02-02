package com.springboot.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private  String email;
    @Column(name="body")
    private  String body;

    @ManyToOne(fetch = FetchType.LAZY) //The Fetch Type.Lazy tells the Hibernate to only fetch the related entities from the database when you use the relationship
    @JoinColumn(name="post_id",nullable = false)
    private Post post;
}
