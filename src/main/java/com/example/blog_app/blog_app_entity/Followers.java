package com.example.blog_app.blog_app_entity;


import jakarta.persistence.*;

@Entity
@Table(name = "follows")
public class Followers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User follower;


    @ManyToOne
    private User following;

    public Followers() {
    }

    public Followers(User following, User follower) {
        this.following = following;
        this.follower = follower;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public User getFollowing() {
        return following;
    }

    public void setFollowing(User following) {
        this.following = following;
    }
}
