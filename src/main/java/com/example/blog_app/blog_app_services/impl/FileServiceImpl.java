package com.example.blog_app.blog_app_services.impl;

import com.example.blog_app.blog_app_services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadimage(String path, MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        String randomid = UUID.randomUUID().toString();
        String filename1 = randomid.concat(name.substring(name.lastIndexOf(".")));

        String filepath = path + File.separator + filename1;

        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }
        Files.copy(file.getInputStream(), Paths.get(filepath));
        return filename1;
    }

    @Override
    public InputStream getresource(String path, String filename) throws FileNotFoundException {
        String fullpath = path+File.separator+filename;
        InputStream is = new FileInputStream(fullpath);
        return is;
    }
}
