package com.example.blog_app.blog_app_entity;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "roles")
public class Role {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;


    public Role() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
