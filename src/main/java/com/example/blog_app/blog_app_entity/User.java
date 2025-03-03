package com.example.blog_app.blog_app_entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	public User(int id, String email, String name, String password, String about) {
		this.id = id;
		this.email = email;
		this.name = name;
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

	private String name;
	
	
	private String email;
	
	
	private String password;
	
	
	private String about;
}
