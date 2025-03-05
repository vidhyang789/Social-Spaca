package com.example.blog_app.blog_app_services.impl;

import com.example.blog_app.blog_app_entity.Category;
import com.example.blog_app.blog_app_exception.ResourceNotFoundException;
import com.example.blog_app.blog_app_payloads.categoryDto;
import com.example.blog_app.blog_app_repositories.categoryRepo;
import com.example.blog_app.blog_app_services.categoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class categoryServiceimpl implements categoryService {


    @Autowired
    private categoryRepo cr;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    public categoryDto createcategory(categoryDto catdto) {
        Category cat = dtotocat(catdto);
        Category saved = cr.save(cat);
        categoryDto saveddto = cattodto(saved);
        return saveddto;
    }

    @Override
    public categoryDto updatecategory(categoryDto catdto, Integer catid) {
        Category cat = cr.findById(catid).orElseThrow(() -> new ResourceNotFoundException("category","id",catid));
        cat.setCategoryDesc(catdto.getCategoryDesc());
        cat.setCategoryTitle(catdto.getCategoryTitle());

        Category updated = cr.save(cat);
        return cattodto(updated);
    }

    @Override
    public categoryDto getcategory(Integer catid) {
        Category cat = cr.findById(catid).orElseThrow(() -> new ResourceNotFoundException("category","id",catid));
        return cattodto(cat);
    }

    @Override
    public List<categoryDto> findallcategory() {
        List<Category> catall = cr.findAll();
        List<categoryDto> alldto = catall.stream().map(cat->cattodto(cat)).collect(Collectors.toList());

        return alldto;
    }

    @Override
    public void deletecategory(Integer catid) {
        Category cat = cr.findById(catid).orElseThrow(() -> new ResourceNotFoundException("category","id",catid));
        cr.delete(cat);
    }

    private Category dtotocat(categoryDto categorydto) {
        Category cat = modelMapper.map(categorydto, Category.class);
        return cat;
    }
    private categoryDto cattodto(Category cat) {
        categoryDto categorydto = modelMapper.map(cat,categoryDto.class);
        return categorydto;
    }
}
