package com.example.blog_app.blog_app_payloads;



public class userdto {
	private int id;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public userdto() {
	}

	public userdto(int id, String name, String email, String password, String about) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
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

	private String name;
	
	
	private String email;
	
	
	private String password;
	
	
	private String about;
}
