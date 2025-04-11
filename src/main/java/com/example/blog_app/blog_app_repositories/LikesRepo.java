package com.example.blog_app.blog_app_repositories;

import com.example.blog_app.blog_app_entity.Likes;
import com.example.blog_app.blog_app_entity.Post;
import com.example.blog_app.blog_app_entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikesRepo extends JpaRepository<Likes,Integer> {
    Optional<Likes> findByUserAndPost(User user, Post post);
}
