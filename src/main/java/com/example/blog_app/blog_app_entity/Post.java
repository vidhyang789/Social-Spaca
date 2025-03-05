package com.example.blog_app.blog_app_entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postid;
    private String title;
    private String content;
    private String postimage;
    private Date addeddate;

    @ManyToOne
    private Category category;
    @ManyToOne
    private User user;

    public Post() {
    }

    public Post(Integer postid, String title, String content, String postimage, Date addeddate, Category category, User user) {
        this.postid = postid;
        this.title = title;
        this.content = content;
        this.postimage = postimage;
        this.addeddate = addeddate;
        this.category = category;
        this.user = user;
    }

    public Integer getPostid() {
        return postid;
    }

    public void setPostid(Integer postid) {
        this.postid = postid;
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

    public Date getAddeddate() {
        return addeddate;
    }

    public void setAddeddate(Date addeddate) {
        this.addeddate = addeddate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
