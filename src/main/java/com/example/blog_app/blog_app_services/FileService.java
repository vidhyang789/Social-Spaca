package com.example.blog_app.blog_app_services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Service
public interface FileService {
    String uploadimage(String path, MultipartFile file) throws IOException;
    InputStream getresource(String path,String filename) throws FileNotFoundException;
}
