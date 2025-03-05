package com.example.blog_app.blog_app_payloads;

import com.example.blog_app.blog_app_entity.Category;
import com.example.blog_app.blog_app_entity.User;

import java.util.Date;

public class PostDto {
    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    private int postid;
    private String title;
    private String content;
    private String postimage;
    private Date addedDate;

    private categoryDto Category;

    private userdto user;

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
