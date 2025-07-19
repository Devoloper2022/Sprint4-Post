package com.sonik.practicum.models.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment {
    private Long id;
    private String comment;
    private Long postId;


    public Comment(Long id, String comment) {
        this.comment = comment;
        this.id = id;
    }
}
