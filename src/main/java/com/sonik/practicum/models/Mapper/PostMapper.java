package com.sonik.practicum.models.Mapper;

import com.sonik.practicum.dto.CommentDto;
import com.sonik.practicum.dto.PostDto;
import com.sonik.practicum.dto.PostsDto;
import com.sonik.practicum.models.Entity.Post;

import java.util.Arrays;
import java.util.List;

public class PostMapper {
    public static PostsDto toDto(Post post, List<CommentDto> comments) {
        PostsDto dto = new PostsDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setParagraphs(
                Arrays.asList(
                        post.getContent()
                                .split("\\r?\\n\\r?\\n")));
        dto.setTags(Arrays.asList(post.getTags().split(" ")));
        dto.setLikes(post.getLikes());
        dto.setComments(comments);
        dto.setImage(post.getImage());
        return dto;
    }

    public static Post toEntity(PostDto dto) {
        Post post = new Post();

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setTags(dto.getTags());
        post.setLikes(0);
        post.setImage(dto.getImage().getOriginalFilename());
        return post;
    }

}
