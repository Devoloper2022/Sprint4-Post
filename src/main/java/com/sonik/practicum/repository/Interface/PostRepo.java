package com.sonik.practicum.repository.Interface;

import com.sonik.practicum.dto.PostDto;
import com.sonik.practicum.models.Entity.Post;

import java.util.List;

public interface PostRepo {
    List<Post> findAll();
    void save(Post post);
    void deleteById(Long id);
    void update(Post post);
    Post findById(Long id);
}
