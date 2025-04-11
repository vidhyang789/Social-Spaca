package com.example.blog_app.blog_app_services.impl;


import com.example.blog_app.blog_app_entity.Followers;
import com.example.blog_app.blog_app_entity.User;
import com.example.blog_app.blog_app_exception.ResourceNotFoundException;
import com.example.blog_app.blog_app_payloads.userdto;
import com.example.blog_app.blog_app_repositories.FollowerRepo;
import com.example.blog_app.blog_app_repositories.userrepo;
import com.example.blog_app.blog_app_services.FollowersService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FollowersServiceImpl implements FollowersService {

    @Autowired
    private FollowerRepo followerRepository;

    @Autowired
    private userrepo userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public String followOrUnfollowUser(int followerId, int followingId) {
        if (followerId == followingId) {
            throw new IllegalArgumentException("A user cannot follow themselves.");
        }

        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new ResourceNotFoundException("user","user id",followerId));

        User following = userRepository.findById(followingId)
                .orElseThrow(() -> new ResourceNotFoundException("user","user id",followingId));

        Optional<Followers> existingFollower = followerRepository.findByFollowerAndFollowing(follower, following);

        if (existingFollower.isPresent()) {
            followerRepository.delete(existingFollower.get());
            return "Unfollowed successfully!";
        } else {
            followerRepository.save(new Followers(following, follower));
            return "Followed successfully!";
        }
    }

    @Override
    public List<userdto> getFollowers(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user","user id",userId));

        // Extract the followers (users) and convert them to DTOs
        return followerRepository.findByFollowing(user).stream().map(f -> modelMapper.map(f.getFollower(), userdto.class)).collect(Collectors.toList());
    }

    @Override
    public List<userdto> getFollowing(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","userid",userId));

        return followerRepository.findByFollower(user).stream().map(f -> modelMapper.map(f.getFollowing(), userdto.class)).collect(Collectors.toList());
    }
}