package com.example.blog_app.blog_app_services;

import com.example.blog_app.blog_app_payloads.LikesDto;
import com.example.blog_app.blog_app_payloads.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LikesService {
    public String likeOrDislikePost(int userid, int postid,boolean likeStatus);
    public int countLikesForPost(int postid);
}
