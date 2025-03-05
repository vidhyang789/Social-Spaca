package com.example.blog_app.blog_app_controllers;

import com.example.blog_app.blog_app_payloads.userdto;
import com.example.blog_app.blog_app_services.userservice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private userservice Userservice;
	
	@PostMapping("/create")
	public ResponseEntity<?> createUser(@Valid @RequestBody userdto Userdto){
		userdto a = Userservice.createuser(Userdto);
		return new ResponseEntity<>(a,HttpStatus.CREATED);
	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<?> updateUser(@Valid @RequestBody userdto Userdto,@PathVariable int id){
		userdto a = Userservice.updateuser(Userdto, id);
		return new ResponseEntity<>(a,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> getall(){
		List<userdto> all = Userservice.findall();
		return new ResponseEntity<>(all,HttpStatus.OK);
	}

	@GetMapping("id/{id}")
	public ResponseEntity<?> getbyid(@PathVariable int id){
		userdto Userdto = Userservice.findbyid(id);
		return new ResponseEntity<>(Userdto,HttpStatus.OK);
	}

	@DeleteMapping("id/{id}")
	public ResponseEntity<?> deletebyid(@PathVariable int id){
		Userservice.deleteuser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
