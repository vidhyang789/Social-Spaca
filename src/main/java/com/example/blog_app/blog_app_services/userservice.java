package com.example.blog_app.blog_app_services;

import java.util.List;

import com.example.blog_app.blog_app_payloads.userdto;
import org.springframework.stereotype.Service;

@Service
public interface userservice {
	userdto registeruser(userdto Userdto);
	userdto createuser(userdto user);
	userdto updateuser(userdto user,int id);
	userdto findbyid(int id);
	List<userdto> findall();
	void deleteuser(int id);
}
