package com.example.blog_app.blog_app_services.impl;

import com.example.blog_app.blog_app_entity.Comment;
import com.example.blog_app.blog_app_entity.Post;
import com.example.blog_app.blog_app_entity.User;
import com.example.blog_app.blog_app_exception.ResourceNotFoundException;
import com.example.blog_app.blog_app_payloads.CommentDto;
import com.example.blog_app.blog_app_repositories.CommentsRepo;
import com.example.blog_app.blog_app_repositories.postrepo;
import com.example.blog_app.blog_app_repositories.userrepo;
import com.example.blog_app.blog_app_services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentsRepo comeentrepo;

    @Autowired
    private ModelMapper modelmapper;

    @Autowired
    private userrepo Userrepo;

    @Autowired
    private postrepo Postrepo;

    @Override
    public CommentDto postcomment(CommentDto commentDto,int postid,int userid) {
        Post post = Postrepo.findById(postid).orElseThrow(()->new ResourceNotFoundException("post","post id",postid));
        User user = Userrepo.findById(userid).orElseThrow(()->new ResourceNotFoundException("user","user id",userid));
        Comment comment = modelmapper.map(commentDto, Comment.class);
        comment.setPost(post);
        comment.setUser(user);
        Comment saved = comeentrepo.save(comment);
        return modelmapper.map(saved,CommentDto.class);
    }

    @Override
    public void deletecomment(int id) {
        Comment comment = comeentrepo.findById(id).orElseThrow(()->new ResourceNotFoundException("comment","comment id",id));
        comeentrepo.delete(comment);
    }
}
