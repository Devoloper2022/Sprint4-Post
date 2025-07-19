package com.sonik.practicum.service;

import com.sonik.practicum.dto.CommentDto;
import com.sonik.practicum.models.Entity.Comment;
import com.sonik.practicum.models.Mapper.CommentMapper;
import com.sonik.practicum.repository.Interface.CommentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepo repo;

    public CommentService(CommentRepo repo) {
        this.repo = repo;
    }

    public List<CommentDto> findAll(Long postId) {
        return repo.findAll(postId).stream()
                .map(CommentMapper::toDto)
                .collect(Collectors.toList());
    }

    public CommentDto findById(Long id) {
        return CommentMapper.toDto(repo.findById(id));
    }


    public void save(CommentDto dto, Long postId) {
        Comment comment = CommentMapper.toEntity(dto, postId);
        repo.save(comment);
    }

    public void update(CommentDto dto, Long postId) {
        Comment comment = CommentMapper.toEntity(dto, postId);
        repo.update(comment);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
