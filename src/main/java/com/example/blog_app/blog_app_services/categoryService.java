package com.example.blog_app.blog_app_services;

import com.example.blog_app.blog_app_payloads.categoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface categoryService {
    categoryDto createcategory(categoryDto user);
    categoryDto updatecategory(categoryDto user,Integer catid);
    categoryDto getcategory(Integer catid);
    List<categoryDto> findallcategory();
    void deletecategory(Integer catid);
}
