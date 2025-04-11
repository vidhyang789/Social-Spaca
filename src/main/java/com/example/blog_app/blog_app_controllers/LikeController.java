package com.example.blog_app.blog_app_controllers;

import com.example.blog_app.blog_app_payloads.PostDto;
import com.example.blog_app.blog_app_services.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like")
public class LikeController {

    @Autowired
    private LikesService likeService;

    @PostMapping("like/{userId}/post/{postId}")
    public ResponseEntity<String> likeOrDislike(
            @PathVariable int userId,
            @PathVariable int postId,
            @RequestParam boolean like) {

        String message = likeService.likeOrDislikePost(userId, postId, like);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/count/{postId}")
    public ResponseEntity<Integer> getLikesCount(@PathVariable int postId) {
        int count = likeService.countLikesForPost(postId);
        return ResponseEntity.ok(count);
    }


}
