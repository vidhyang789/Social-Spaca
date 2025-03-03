package com.example.blog_app.blog_app_services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.example.blog_app.blog_app_entity.User;
import com.example.blog_app.blog_app_exception.ResourceNotFoundException;
import com.example.blog_app.blog_app_payloads.userdto;
import com.example.blog_app.blog_app_repositories.userrepo;
import com.example.blog_app.blog_app_services.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class userserviceimpl implements userservice {
	
	@Autowired
	private userrepo ur;

	@Override
	public userdto createuser(userdto userdto) {
		User user = dtotouser(userdto);
		
		User saved = ur.save(user);
		
		userdto saveddto = usertodto(saved);
		
		return saveddto;
		
	}

	@Override
	public userdto updateuser(userdto Userdto, int id) {
		User user = ur.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","id",id));
		user.setName(Userdto.getName());
		user.setEmail(Userdto.getEmail());
		user.setPassword(Userdto.getPassword());
		user.setAbout(Userdto.getAbout());
		
		User updated = ur.save(user);
		return usertodto(updated);
	}

	@Override
	public userdto findbyid(int id) {
		User user = ur.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","id",id));
		
		return usertodto(user);
	}

	@Override
	public List<userdto> findall() {
		List<User> all = ur.findAll();
		List<userdto> alldto = all.stream().map(user->usertodto(user)).collect(Collectors.toList());
		return alldto;
	}

	@Override
	public void deleteuser(int id) {
		User user = ur.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","id",id));
		ur.delete(user);
	}
	
	private User dtotouser(userdto Userdto) {
		User user = new User();
		user.setId(Userdto.getId());
		user.setName(Userdto.getName());
		user.setEmail(Userdto.getEmail());
		user.setPassword(Userdto.getPassword());
		user.setAbout(Userdto.getAbout());
		return user;
	}
	private userdto usertodto(User user) {
		userdto Userdto = new userdto();
		Userdto.setId(user.getId());
		Userdto.setName(user.getName());
		Userdto.setEmail(user.getEmail());
		Userdto.setPassword(user.getPassword());
		Userdto.setAbout(user.getAbout());
		return Userdto;
	}

}
