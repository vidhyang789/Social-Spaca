package com.example.blog_app.blog_app_controllers;

import com.example.blog_app.blog_app_entity.Post;
import com.example.blog_app.blog_app_payloads.Apiresponse;
import com.example.blog_app.blog_app_payloads.PostDto;
import com.example.blog_app.blog_app_payloads.PostResponse;
import com.example.blog_app.blog_app_services.FileService;
import com.example.blog_app.blog_app_services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("post")
public class PostController {

    @Autowired
    private PostService postservice;

    @Autowired
    private FileService fileservice;

    @Value("${project.image}")
    private String path;

    @PostMapping("/userid/{userid}/catid/{catid}/create")
    public ResponseEntity<?> createpost(@RequestBody PostDto postDto, @PathVariable int userid,@PathVariable int catid){
        PostDto saveddto = postservice.createpost(postDto,catid,userid);
        return new ResponseEntity<>(saveddto, HttpStatus.CREATED);
    }


    @GetMapping("/userid/{userid}/posts")
    public ResponseEntity<?> getbyuser(@PathVariable int userid){
        List<PostDto> all = postservice.getPostbyuserid(userid);
        return new ResponseEntity<>(all,HttpStatus.OK);
    }

    @GetMapping("/catid/{catid}/posts")
    public ResponseEntity<?> getbycat(@PathVariable int catid){
        List<PostDto> all = postservice.getPostbycatid(catid);
        return new ResponseEntity<>(all,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getall(
            @RequestParam(value = "pagenumber",defaultValue = "0",required = false) int pagenumber,
            @RequestParam(value = "pagesize",defaultValue = "10",required = false) int pagesize
    ){
        PostResponse all = postservice.getallpost(pagenumber,pagesize);
        return new ResponseEntity<>(all,HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getbyid(@PathVariable int id){
        PostDto post = postservice.getPostbyid(id);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public Apiresponse deletepost(@PathVariable int id){
        postservice.deletePost(id);
        return new Apiresponse("Post id successfully deleted !",true);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updatepost(@RequestBody PostDto postdto,@PathVariable int id){
        PostDto saved = postservice.updatePost(postdto,id);
        return new ResponseEntity<>(saved,HttpStatus.OK);
    }

    @GetMapping("/title/{t}")
    public ResponseEntity<?> searchpost(@PathVariable String t){
        List<PostDto> all = postservice.searchPost(t);
        return new ResponseEntity<>(all,HttpStatus.OK);
    }

    @PostMapping("/image/upload/{postid}")
    public ResponseEntity<?> uploadimage(
            @RequestParam("image") MultipartFile image,
            @PathVariable int postid) throws IOException {
        String filename = fileservice.uploadimage(path,image);
        PostDto post = postservice.getPostbyid(postid);
        post.setPostimage(filename);
        PostDto updated = postservice.updatePost(post,postid);
        return new ResponseEntity<>(updated,HttpStatus.OK);
    }

    @GetMapping(value = "/image/{imagename}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadimage(
            @PathVariable String imagename,
            HttpServletResponse response
    ) throws IOException{
        InputStream resource = this.fileservice.getresource(path,imagename);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

    @GetMapping("/user/{userId}/liked-posts")
    public ResponseEntity<List<PostDto>> getLikedPosts(@PathVariable int userId) {
        List<PostDto> likedPosts = postservice.getPostsLikedByUser(userId);
        return ResponseEntity.ok(likedPosts);
    }


}
