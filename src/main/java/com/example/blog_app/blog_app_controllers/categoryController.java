package com.example.blog_app.blog_app_controllers;

import com.example.blog_app.blog_app_payloads.categoryDto;
import com.example.blog_app.blog_app_services.categoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("category")
public class categoryController {

    @Autowired
    private categoryService categoryservice;

    @GetMapping
    public ResponseEntity<?> getall(){
        List<categoryDto> all = categoryservice.findallcategory();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getbyid(@PathVariable int id){
        categoryDto cat = categoryservice.getcategory(id);
        return new ResponseEntity<>(cat,HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createcategory(@Valid @RequestBody categoryDto cat){
        categoryDto saved = categoryservice.createcategory(cat);
        return new ResponseEntity<>(saved,HttpStatus.CREATED);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updatecategory(@Valid @RequestBody categoryDto cat,@PathVariable int id){
        categoryDto saved = categoryservice.updatecategory(cat,id);
        return new ResponseEntity<>(saved,HttpStatus.CREATED);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deletecategory(@PathVariable int id){
        categoryservice.deletecategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
