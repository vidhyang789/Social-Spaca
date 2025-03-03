package com.example.blog_app.blog_app_repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog_app.blog_app_entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface userrepo extends JpaRepository<User,Integer>{
	
}
