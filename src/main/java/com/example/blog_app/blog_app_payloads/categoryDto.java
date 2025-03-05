package com.example.blog_app.blog_app_payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class categoryDto {
    private Integer categoryId;

    @NotEmpty
    @Size(min = 3,message = "size must be atleast 3")
    private String categoryTitle;

    @NotEmpty
    @Size(min = 10,message = "size must be atleast 10")
    private String categoryDesc;

    public categoryDto() {
    }

    public categoryDto(Integer categoryId, String categoryTitle, String categoryDesc) {
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
        this.categoryDesc = categoryDesc;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }
}
