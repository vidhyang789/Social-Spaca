package com.example.blog_app.blog_app_services;

import com.example.blog_app.blog_app_entity.User;
import com.example.blog_app.blog_app_payloads.userdto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FollowersService {
    public String followOrUnfollowUser(int followerId, int followingId);
    public List<userdto> getFollowers(int userId);
    public List<userdto> getFollowing(int userId);
}
