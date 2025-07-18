package com.sonik.practicum.dto;

import org.springframework.web.multipart.MultipartFile;

public class PostDto {
    private Long id;
    private String title;
    private String content;
    private String tags;
    private Integer likes;
    private MultipartFile image;
}
