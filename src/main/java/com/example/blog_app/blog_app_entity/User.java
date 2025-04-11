package com.example.blog_app.blog_app_entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<Post> posts;

	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private Set<Comment> comments;

	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private Set<Likes> likes;

	@OneToMany(mappedBy = "follower",cascade = CascadeType.ALL)
	private Set<Followers> followers;

	@OneToMany(mappedBy = "following",cascade = CascadeType.ALL)
	private Set<Followers> following;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "user_role",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Role> roles = new HashSet<>();

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Followers> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<Followers> followers) {
		this.followers = followers;
	}

	public Set<Followers> getFollowing() {
		return following;
	}

	public void setFollowing(Set<Followers> following) {
		this.following = following;
	}

	public Set<Likes> getLikes() {
		return likes;
	}

	public void setLikes(Set<Likes> likes) {
		this.likes = likes;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}


	public User() {
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getAbout() {
		return about;
	}

	@NotEmpty
	@Size(min = 3,message = "name must have atleast 3 chars !!")
	private String name;
	
	@Email(message = "enter valid email !!")
	@Column(nullable = false, unique = true)
	private String email;
	
	@NotEmpty
	@Size(min = 3,max = 255,message = "password must have atleast 3 chars and atmost 255 chars !!")
	private String password;

	@NotEmpty
	private String about;

	public String getPassword() {
		return password;
	}
}
