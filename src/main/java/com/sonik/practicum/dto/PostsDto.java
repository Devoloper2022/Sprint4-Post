package com.sonik.practicum.dto;


import lombok.Data;

import java.util.List;

@Data
public class PostsDto {
    private Long id;
    private String title;
    private List<String> paragraphs;
    private List<String> tags;
    private Integer likes;
    private List<CommentDto> comments;
    private String image;



    public String getTagsAsText() {
        return String.join(" ", tags);
    }

    public String getParagraphsAsText() {
        return String.join("\n ", paragraphs);
    }
}
