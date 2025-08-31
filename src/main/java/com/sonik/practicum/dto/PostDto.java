package com.sonik.practicum.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PostDto {
    private String title;
    private String content;
    private String tags;
    private MultipartFile image;
}
