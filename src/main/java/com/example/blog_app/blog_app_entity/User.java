package com.example.blog_app.blog_app_entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
@Table(name = "users")

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<Post> posts;

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public User(int id, Set<Post> posts, String name, String email, String password, String about) {
		this.id = id;
		this.posts = posts;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
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

	public String getPassword() {
		return password;
	}

	public String getAbout() {
		return about;
	}

	@NotEmpty
	@Size(min = 3,message = "name must have atleast 3 chars !!")
	private String name;
	
	@Email(message = "enter valid email !!")
	private String email;
	
	@NotEmpty
	@Size(min = 3,max = 10,message = "password must have atleast 3 chars and atmost 10 chars !!")
	private String password;
	
	@NotEmpty
	private String about;
}
