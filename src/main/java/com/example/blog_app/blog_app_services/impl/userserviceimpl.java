package com.example.blog_app.blog_app_services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.blog_app.blog_app_entity.Role;
import com.example.blog_app.blog_app_entity.User;
import com.example.blog_app.blog_app_exception.ResourceNotFoundException;
import com.example.blog_app.blog_app_payloads.userdto;
import com.example.blog_app.blog_app_repositories.Rolesrepo;
import com.example.blog_app.blog_app_repositories.userrepo;
import com.example.blog_app.blog_app_services.userservice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class userserviceimpl implements userservice {
	
	@Autowired
	private userrepo ur;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private Rolesrepo rolesrepo;


	@Autowired
	private ModelMapper modelMapper;


	@Override
	public userdto registeruser(userdto Userdto) {
		User user = modelMapper.map(Userdto,User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		if (user.getRoles() == null) {
			user.setRoles(new HashSet<>()); // Initialize if null
		}

		Role role = this.rolesrepo.findById(502).get();
		user.getRoles().add(role);

		User newuser = ur.save(user);

		return this.modelMapper.map(newuser,userdto.class);
	}

	@Override
	public userdto createuser(userdto userdto) {
		User user = dtotouser(userdto);

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		if (ur.existsByEmail(user.getEmail())) {
			throw new IllegalArgumentException("Email already exists. Please use another email.");
		}

		User saved = ur.save(user);

        return usertodto(saved);
		
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
        return all.stream().map(this::usertodto).collect(Collectors.toList());
	}

	@Override
	public void deleteuser(int id) {
		User user = ur.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","id",id));
		ur.delete(user);
	}
	
	private User dtotouser(userdto Userdto) {
		User user = modelMapper.map(Userdto,User.class);
		return user;
	}
	private userdto usertodto(User user) {
		userdto Userdto = modelMapper.map(user,userdto.class);
		return Userdto;
	}

}
