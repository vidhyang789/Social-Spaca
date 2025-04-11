package com.example.blog_app;

import com.example.blog_app.blog_app_entity.Role;
import com.example.blog_app.blog_app_repositories.Rolesrepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BlogAppApplication implements CommandLineRunner{

	@Autowired
	private Rolesrepo rolesrepo;

	@Autowired
	private PasswordEncoder passwordEncoder;


	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		try{
			Role role = new Role();
			role.setId(501);
			role.setName("ADMIN_USER");


			Role role1 = new Role();
			role1.setId(502);
			role1.setName("NORMAL_USER");


			List<Role> roles = List.of(role,role1);

			List<Role> result = rolesrepo.saveAll(roles);

			result.forEach(r ->{
				System.out.println(r.getName());
			});

		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
