package com.example.blog_app.blog_app_repositories;

import com.example.blog_app.blog_app_entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Rolesrepo extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String name);
}
