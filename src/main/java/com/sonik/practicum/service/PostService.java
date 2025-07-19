package com.sonik.practicum.service;

import com.sonik.practicum.dto.CommentDto;
import com.sonik.practicum.dto.PostDto;
import com.sonik.practicum.models.Entity.Post;
import com.sonik.practicum.models.Mapper.PostMapper;
import com.sonik.practicum.repository.Interface.PostRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepo postRepo;
    private final CommentService commentService;


    public PostService(PostRepo postRepo, CommentService commentService) {
        this.postRepo = postRepo;
        this.commentService = commentService;
    }

    public List<PostDto> findAll() {

        return postRepo.findAll().stream()
                .map(post -> {
                    return PostMapper.toDto(post, commentService.findAll(post.getId()));
                })
                .collect(Collectors.toList());

    }

    public PostDto findById(Long id) {
        return PostMapper.toDto(postRepo.findById(id),commentService.findAll(id));
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
