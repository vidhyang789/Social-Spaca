package com.example.blog_app.blog_app_repositories;

import com.example.blog_app.blog_app_entity.Post;
import com.example.blog_app.blog_app_entity.User;
import com.example.blog_app.blog_app_entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface postrepo extends JpaRepository<Post,Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByTitleContaining(String title);
}
