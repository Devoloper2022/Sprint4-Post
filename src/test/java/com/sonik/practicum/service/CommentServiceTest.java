package com.sonik.practicum.service;

import com.sonik.practicum.dto.CommentDto;
import com.sonik.practicum.models.Entity.Comment;
import com.sonik.practicum.repository.Interface.CommentRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @InjectMocks
    private CommentService commentService;

    @Mock
    private CommentRepo commentRepo;




    @Test
    void testFindAll() {
        Long postId = 1L;

        Comment comment = new Comment(1L, "test comment", postId);
        when(commentRepo.findAll(postId)).thenReturn(List.of(comment));

        List<CommentDto> result = commentService.findAll(postId);

        assertEquals(1, result.size());
        assertEquals("test comment", result.get(0).getComment());
    }


    @Test
    void testFindById() {
        Long id = 1L;
        Comment comment = new Comment(id, "some text", 99L);
        when(commentRepo.findById(id)).thenReturn(comment);

        CommentDto dto = commentService.findById(id);

        assertEquals("some text", dto.getComment());
        assertEquals(id, dto.getId());
    }


    @Test
    void testFindEntity() {
        Comment comment = new Comment(42L, "entity", 5L);
        when(commentRepo.findById(42L)).thenReturn(comment);

        Comment result = commentService.findEntity(42L);
        assertEquals("entity", result.getComment());
    }


    @Test
    void testSave() {
        Long postId = 1L;
        CommentDto dto = new CommentDto(null, "new comment");

        commentService.save(dto, postId);

        ArgumentCaptor<Comment> captor = ArgumentCaptor.forClass(Comment.class);
        verify(commentRepo).save(captor.capture());

        Comment saved = captor.getValue();
        assertEquals("new comment", saved.getComment());
        assertEquals(postId, saved.getPostId());
    }


    @Test
    void testUpdate() {
        CommentDto dto = new CommentDto(1L, "updated comment");
        Comment existing = new Comment(1L, "old comment", 99L);

        when(commentRepo.findById(1L)).thenReturn(existing);

        commentService.update(dto);

        assertEquals("updated comment", existing.getComment());
        verify(commentRepo).update(existing);
    }

    @Test
    void testDeleteById() {
        commentService.deleteById(123L);
        verify(commentRepo).deleteById(123L);
    }





}
