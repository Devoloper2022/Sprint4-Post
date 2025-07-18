package com.sonik.practicum.service;

import com.sonik.practicum.models.Post;
import com.sonik.practicum.repository.Interface.PostRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepo postRepo;


    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    public List<Post> findAll() {
        return postRepo.findAll();
    }


    public void save(Post post) {
        postRepo.save(post);
    }

    public void update(Post post) {
        postRepo.update(post);
    }

    public void deleteById(Long id) {
        postRepo.deleteById(id);
    }
}
