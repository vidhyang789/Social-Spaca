package com.example.blog_app.blog_app_repositories;

import com.example.blog_app.blog_app_entity.Followers;
import com.example.blog_app.blog_app_entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowerRepo extends JpaRepository<Followers,Integer> {
        Optional<Followers> findByFollowerAndFollowing(User follower, User following);
        List<Followers> findByFollowing(User user); // Get followers of a user
        List<Followers> findByFollower(User user);  // Get users a user is following
}
