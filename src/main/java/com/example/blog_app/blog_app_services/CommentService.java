package com.example.blog_app.blog_app_services;

import com.example.blog_app.blog_app_payloads.CommentDto;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    CommentDto postcomment(CommentDto commentDto,int postid,int userid);
    void deletecomment(int id);
}
