package com.sonik.practicum.repository.Interface;

import com.sonik.practicum.models.Entity.Comment;

import java.util.List;

public interface CommentRepo {
    List<Comment> findAll(Long postId);
    void save(Comment comments);
    void deleteById(Long id);
    void update(Comment comments);
    Comment findById(Long id);
}
