package com.example.blog_app.blog_app_payloads;


import com.example.blog_app.blog_app_entity.Followers;
import com.example.blog_app.blog_app_entity.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.Set;

public class userdto {
	private int id;

	private Set<CommentDto> comments;


	@OneToMany(mappedBy = "follower",cascade = CascadeType.ALL)
	private Set<FollowersDto> followers;

	@OneToMany(mappedBy = "following",cascade = CascadeType.ALL)
	private Set<FollowersDto> following;

	public Set<FollowersDto> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<FollowersDto> followers) {
		this.followers = followers;
	}

	public Set<FollowersDto> getFollowing() {
		return following;
	}

	public void setFollowing(Set<FollowersDto> following) {
		this.following = following;
	}

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

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public userdto() {
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getAbout() {
		return about;
	}

	private Set<RoleDto> roles;

	public Set<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDto> roles) {
		this.roles = roles;
	}

	private String name;
	
	
	private String email;
	
	
	private String password;
	
	
	private String about;
}
