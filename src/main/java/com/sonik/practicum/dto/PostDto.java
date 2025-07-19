package com.sonik.practicum.dto;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private List<String> tags;
    private Integer likes;
    private List<CommentDto> comment;
}
