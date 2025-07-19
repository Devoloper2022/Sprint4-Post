package com.sonik.practicum.models.Mapper;

import com.sonik.practicum.dto.CommentDto;
import com.sonik.practicum.dto.PostDto;
import com.sonik.practicum.models.Entity.Post;
import com.sonik.practicum.models.Entity.Comment;

import java.util.Arrays;
import java.util.List;

public class PostMapper {
    public static PostDto toDto(Post post, List<CommentDto> comments) {
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setTags(Arrays.asList(post.getTags().split(" ")));
        dto.setLikes(post.getLikes());
        dto.setComment(comments);
        return dto;
    }

    public static Post toEntity(PostDto dto) {
        Post post = new Post();
        post.setId(dto.getId());
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setTags(String.valueOf(dto.getTags()));
        post.setLikes(dto.getLikes());
        return post;
    }
}
