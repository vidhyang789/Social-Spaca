package com.example.blog_app.blog_app_repositories;

import com.example.blog_app.blog_app_entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface categoryRepo extends JpaRepository<Category,Integer> {
}
