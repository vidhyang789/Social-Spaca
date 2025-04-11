package com.example.blog_app.blog_app_controllers;

import com.example.blog_app.blog_app_entity.Comment;
import com.example.blog_app.blog_app_payloads.Apiresponse;
import com.example.blog_app.blog_app_payloads.CommentDto;
import com.example.blog_app.blog_app_services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentservice;

    @PostMapping("/create/post/{postid}/user/{userid}")
    public ResponseEntity<?> createcomment(
            @RequestBody CommentDto commentdto,
            @PathVariable int postid,
            @PathVariable int userid
    ){
        CommentDto saved = commentservice.postcomment(commentdto,postid,userid);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deletepost(@PathVariable int id){
        commentservice.deletecomment(id);
        return new ResponseEntity<>(new Apiresponse("comment is deleted successfully",true),HttpStatus.OK);
    }

}
