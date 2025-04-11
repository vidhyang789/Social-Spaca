package com.example.blog_app.blog_app_services.impl;

import com.example.blog_app.blog_app_entity.Likes;
import com.example.blog_app.blog_app_entity.Post;
import com.example.blog_app.blog_app_entity.User;
import com.example.blog_app.blog_app_exception.ResourceNotFoundException;
import com.example.blog_app.blog_app_repositories.LikesRepo;
import com.example.blog_app.blog_app_repositories.postrepo;
import com.example.blog_app.blog_app_repositories.userrepo;
import com.example.blog_app.blog_app_services.LikesService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikesServiceImpl implements LikesService {

    @Autowired
    private LikesRepo likerepo;

    @Autowired
    private userrepo Userrepo;

    @Autowired
    private postrepo Postrepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public String likeOrDislikePost(int userId, int postId, boolean likeStatus) {
        User user = Userrepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User","userid",userId));

        Post post = Postrepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post","Post id",postId));

        Optional<Likes> existingLike = likerepo.findByUserAndPost(user, post);

        if (existingLike.isPresent()) {
            Likes like = existingLike.get();
            if (like.isIsliked() == likeStatus) {
                likerepo.delete(like);
                return likeStatus ? "Like removed" : "Dislike removed";
            } else {
                like.setIsliked(likeStatus);
                likerepo.save(like);
                return likeStatus ? "Liked" : "Disliked";
            }
        } else {
            Likes newLike = new Likes(post, user, likeStatus);
            likerepo.save(newLike);
            return likeStatus ? "Liked" : "Disliked";
        }
    }

    @Override
    public int countLikesForPost(int postId) {
        return likerepo.findAll().stream().filter(like -> like.getPost().getId() == postId && like.isIsliked()).toList().size();
    }

}
