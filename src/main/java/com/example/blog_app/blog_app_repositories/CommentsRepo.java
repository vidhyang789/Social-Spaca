package com.example.blog_app.blog_app_repositories;

import com.example.blog_app.blog_app_entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepo extends JpaRepository<Comment,Integer> {
}
