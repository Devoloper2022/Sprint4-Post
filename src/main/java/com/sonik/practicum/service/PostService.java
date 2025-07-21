package com.sonik.practicum.service;

import com.sonik.practicum.dto.LikeDto;
import com.sonik.practicum.dto.PostDto;
import com.sonik.practicum.dto.PostsDto;
import com.sonik.practicum.models.Entity.Post;
import com.sonik.practicum.models.Mapper.PostMapper;
import com.sonik.practicum.repository.Interface.PostRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Value("${storage.path}")
    private String storePath;

    private final PostRepo postRepo;
    private final CommentService commentService;


    public PostService(PostRepo postRepo, CommentService commentService) {
        this.postRepo = postRepo;
        this.commentService = commentService;
    }

    public List<PostsDto> findAll() {

        return postRepo.findAll().stream()
                .map(post -> {
                    return PostMapper.toDto(post, commentService.findAll(post.getId()));
                })
                .collect(Collectors.toList());

    }

    public PostsDto findById(Long id) {
        return PostMapper.toDto(postRepo.findById(id), commentService.findAll(id));
    }

    public Post findEntityById(Long id) {
        return postRepo.findById(id);
    }

    public void like(LikeDto like, Long id) {
        Post post = findEntityById(id);
        if (like.isLike()) {
            post.setLikes(post.getLikes() + 1);
        }
        else {
            post.setLikes(post.getLikes() - 1);
        }

        postRepo.update(post);
    }

    public void save(String title, String text, String tags, MultipartFile image) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(text);
        post.setTags(tags);
        post.setLikes(0);


        String fileName = saveImage(image);
        post.setImage(fileName);
        postRepo.save(post);

    }

    public void update(String title, String text, String tags, MultipartFile image, Long id) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(text);
        post.setTags(tags);
        post.setLikes(0);


        String fileName = saveImage(image);
        post.setImage(fileName);
        post.setId(id);
        postRepo.update(post);
    }

    public void deleteById(Long id) {
        postRepo.deleteById(id);
    }

    private String saveImage(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            return "";
        }
        try {
            String fileName = file.getOriginalFilename();
            File destination = new File(storePath + fileName);
            file.transferTo(destination); // save the file
        } catch (IOException e) {
            throw new RuntimeException(" <UNK>");
        }


        return file.getOriginalFilename();
    }
}
