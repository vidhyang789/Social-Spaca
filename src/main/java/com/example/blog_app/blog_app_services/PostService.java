package com.example.blog_app.blog_app_services;

import com.example.blog_app.blog_app_payloads.PostDto;
import com.example.blog_app.blog_app_payloads.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    PostDto createpost(PostDto postdto,int catid,int userid);

    PostResponse getallpost(int pagenumber, int pagesize);

    PostDto getPostbyid(int id);

    PostDto updatePost(PostDto postdto,int id);

    void deletePost(int id);

    List<PostDto> getPostbycatid(int Catid);

    List<PostDto> getPostbyuserid(int userid);

    List<PostDto> searchPost(String target);

    public List<PostDto> getPostsLikedByUser(int userId);

}
