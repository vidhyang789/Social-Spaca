package com.example.blog_app.blog_app_payloads;

import java.util.Date;
import java.util.Set;

public class PostDto {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer id;
    private String title;
    private String content;
    private String postimage;
    private Date addedDate;

    private categoryDto Category;

    private userdto user;

    private Set<CommentDto> comments;

    private Set<LikesDto> likes;

    public Set<LikesDto> getLikes() {
        return likes;
    }

    public void setLikes(Set<LikesDto> likes) {
        this.likes = likes;
    }

    public Set<CommentDto> getComments() {
        return comments;
    }

    public void setComments(Set<CommentDto> comments) {
        this.comments = comments;
    }

    public PostDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostimage() {
        return postimage;
    }

    public void setPostimage(String postimage) {
        this.postimage = postimage;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public categoryDto getCategory() {
        return Category;
    }

    public void setCategory(categoryDto category) {
        Category = category;
    }

    public userdto getUser() {
        return user;
    }

    public void setUser(userdto user) {
        this.user = user;
    }
}
