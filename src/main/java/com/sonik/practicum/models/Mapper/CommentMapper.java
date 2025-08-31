package com.sonik.practicum.models.Mapper;

import com.sonik.practicum.dto.CommentDto;

import com.sonik.practicum.models.Entity.Comment;


public class CommentMapper {
    public static CommentDto toDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setComment(comment.getComment());
        return dto;
    }

    public static Comment toEntity(CommentDto dto,Long id) {
        Comment entity = new Comment();
        entity.setId(dto.getId());
        entity.setComment(dto.getComment());
        entity.setPostId(id);
        return entity;
    }
}
