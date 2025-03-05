package com.example.blog_app.blog_app_services.impl;

import com.example.blog_app.blog_app_entity.Post;
import com.example.blog_app.blog_app_entity.User;
import com.example.blog_app.blog_app_entity.Category;
import com.example.blog_app.blog_app_exception.ResourceNotFoundException;
import com.example.blog_app.blog_app_payloads.PostDto;
import com.example.blog_app.blog_app_payloads.PostResponse;
import com.example.blog_app.blog_app_repositories.categoryRepo;
import com.example.blog_app.blog_app_repositories.postrepo;
import com.example.blog_app.blog_app_repositories.userrepo;
import com.example.blog_app.blog_app_services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class postServiceImpl implements PostService {

    @Autowired
    private postrepo Postrepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private userrepo Userrepo;

    @Autowired
    private categoryRepo catrepo;


    @Override
    public PostDto createpost(PostDto postdto,int catid,int userid) {

        Category cat = catrepo.findById(catid).orElseThrow(() -> new ResourceNotFoundException("category","id",catid));
        User user = Userrepo.findById(userid).orElseThrow(() -> new ResourceNotFoundException("user","id",userid));


        Post post = modelMapper.map(postdto,Post.class);
        post.setPostimage("default.png");
        post.setAddeddate(new Date());
        post.setCategory(cat);
        post.setUser(user);


        Post saved = Postrepo.save(post);
        PostDto saveddto = modelMapper.map(saved,PostDto.class);
        return saveddto;
    }

    @Override
    public PostResponse getallpost(int pagenumber, int pagesize) {
        Pageable p = PageRequest.of(pagenumber,pagesize);
        Page<Post> all = Postrepo.findAll(p);
        List<Post> postall = all.getContent();
        List<PostDto> alldto = postall.stream().map(cat->modelMapper.map(cat, PostDto.class)).collect(Collectors.toList());
        PostResponse postresponse = new PostResponse();
        postresponse.setContent(alldto);
        postresponse.setPagenumber(all.getNumber());
        postresponse.setPagesize(all.getSize());
        postresponse.setTotalelements(all.getTotalElements());
        postresponse.setTotalpage(all.getTotalPages());
        postresponse.setLastpage(all.isLast());
        return postresponse;
    }

    @Override
    public PostDto getPostbyid(int id) {
        Post post = Postrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("post","post id",id));
        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postdto, int id) {
        Post post = Postrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("post","post id",id));
        post.setContent(postdto.getTitle());
        post.setContent(postdto.getContent());
        post.setPostimage(postdto.getPostimage());

        Post updated = Postrepo.save(post);
        return modelMapper.map(updated, PostDto.class);
    }

    @Override
    public void deletePost(int id) {
        Post post = Postrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("post","post id",id));
        Postrepo.delete(post);
    }

    @Override
    public List<PostDto> getPostbycatid(int Catid) {
        Category cat = catrepo.findById(Catid).orElseThrow(() -> new ResourceNotFoundException("category","id",Catid));
        List<Post> catlist = Postrepo.findByCategory(cat);
        List<PostDto> alldto = catlist.stream().map(post->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return alldto;
    }

    @Override
    public List<PostDto> getPostbyuserid(int userid) {
        User user = Userrepo.findById(userid).orElseThrow(() -> new ResourceNotFoundException("user","id",userid));
        List<Post> userlist = Postrepo.findByUser(user);
        List<PostDto> alldto = userlist.stream().map(post->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return alldto;
    }

    @Override
    public List<PostDto> searchPost(String target) {
        List<Post> titled = Postrepo.findByTitleContaining(target);
        List<PostDto> alldto = titled.stream().map(post->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return alldto;
    }
}
