package com.example.blog_app.blog_app_controllers;

import com.example.blog_app.blog_app_payloads.userdto;
import com.example.blog_app.blog_app_services.FollowersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class FollowersController {
    @Autowired
    private FollowersService followerService;

    @PostMapping("/{followerId}/follow/{followingId}")
    public ResponseEntity<String> followOrUnfollow(@PathVariable int followerId, @PathVariable int followingId) {
        String message = followerService.followOrUnfollowUser(followerId, followingId);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/user/{userId}/followers")
    public ResponseEntity<List<userdto>> getFollowers(@PathVariable int userId) {
        List<userdto> followers = followerService.getFollowers(userId);
        return new ResponseEntity<>(followers, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/following")
    public ResponseEntity<List<userdto>> getFollowing(@PathVariable int userId) {
        List<userdto> following = followerService.getFollowing(userId);
        return new ResponseEntity<>(following, HttpStatus.OK);
    }
}
